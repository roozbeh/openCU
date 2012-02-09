/** 
 * This code is written by Roozbeh Zabihollahi and with Marc Manthey 
 * partnership.
 *
 * Copyright (c) 2008 iPronto, All Rights Reserved.
 *
 * This software is the confidential and proprietary information
 * of iPronto. ("Confidential Information").  You shall
 * not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into.
 *
 * IPRONTO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. IPRONTO SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

#include "Stdafx.h"
#include "VideoCodec.h"
#include "VideoFrame.h"

#define SMALL_EPS (1e-10)
#define FRAMERATE_INCR 1001

static const int motion_presets[] = {
	/* quality 0 */
	0,

	/* quality 1 */
	XVID_ME_ADVANCEDDIAMOND16,

	/* quality 2 */
	XVID_ME_ADVANCEDDIAMOND16 | XVID_ME_HALFPELREFINE16,

	/* quality 3 */
	XVID_ME_ADVANCEDDIAMOND16 | XVID_ME_HALFPELREFINE16 |
	XVID_ME_ADVANCEDDIAMOND8 | XVID_ME_HALFPELREFINE8,

	/* quality 4 */
	XVID_ME_ADVANCEDDIAMOND16 | XVID_ME_HALFPELREFINE16 |
	XVID_ME_ADVANCEDDIAMOND8 | XVID_ME_HALFPELREFINE8 |
	XVID_ME_CHROMA_PVOP | XVID_ME_CHROMA_BVOP,

	/* quality 5 */
	XVID_ME_ADVANCEDDIAMOND16 | XVID_ME_HALFPELREFINE16 |
	XVID_ME_ADVANCEDDIAMOND8 | XVID_ME_HALFPELREFINE8 |
	XVID_ME_CHROMA_PVOP | XVID_ME_CHROMA_BVOP,

	/* quality 6 */
	XVID_ME_ADVANCEDDIAMOND16 | XVID_ME_HALFPELREFINE16 | XVID_ME_EXTSEARCH16 |
	XVID_ME_ADVANCEDDIAMOND8 | XVID_ME_HALFPELREFINE8 | XVID_ME_EXTSEARCH8 |
	XVID_ME_CHROMA_PVOP | XVID_ME_CHROMA_BVOP,

};
#define ME_ELEMENTS (sizeof(motion_presets)/sizeof(motion_presets[0]))

static const int vop_presets[] = {
	/* quality 0 */
	0,

	/* quality 1 */
	0,

	/* quality 2 */
	XVID_VOP_HALFPEL,

	/* quality 3 */
	XVID_VOP_HALFPEL | XVID_VOP_INTER4V,

	/* quality 4 */
	XVID_VOP_HALFPEL | XVID_VOP_INTER4V,

	/* quality 5 */
	XVID_VOP_HALFPEL | XVID_VOP_INTER4V |
	XVID_VOP_TRELLISQUANT,

	/* quality 6 */
	XVID_VOP_HALFPEL | XVID_VOP_INTER4V |
	XVID_VOP_TRELLISQUANT | XVID_VOP_HQACPRED,

};
#define VOP_ELEMENTS (sizeof(vop_presets)/sizeof(vop_presets[0]))

int XVIDVideoCodec::enc_init()
{
	int xerr;
	//xvid_plugin_cbr_t cbr;
	//xvid_plugin_fixed_t rcfixed;
	xvid_enc_plugin_t plugins[7];
	xvid_gbl_init_t xvid_gbl_init;
	xvid_enc_create_t xvid_enc_create;

	/*------------------------------------------------------------------------
	 * XviD core initialization
	 *----------------------------------------------------------------------*/

	/* Set version -- version checking will done by xvidcore */
	memset(&xvid_gbl_init, 0, sizeof(xvid_gbl_init));
	xvid_gbl_init.version = XVID_VERSION;
    xvid_gbl_init.debug = 0 /*ARG_DEBUG*/;


#ifdef ARCH_IS_IA64
	xvid_gbl_init.cpu_flags = XVID_CPU_FORCE | XVID_CPU_ASM;
#else
	xvid_gbl_init.cpu_flags = 0;
#endif

	/* Initialize XviD core -- Should be done once per __process__ */
	xvid_global(NULL, XVID_GBL_INIT, &xvid_gbl_init, NULL);

	/*------------------------------------------------------------------------
	 * XviD encoder initialization
	 *----------------------------------------------------------------------*/

	/* Version again */
	memset(&xvid_enc_create, 0, sizeof(xvid_enc_create));
	xvid_enc_create.version = XVID_VERSION;

	/* Width and Height of input frames */
	xvid_enc_create.width = m_width;
	xvid_enc_create.height = m_height;
	xvid_enc_create.profile = XVID_PROFILE_AS_L4;

	/* init plugins  */
    xvid_enc_create.zones = ZONES;
    xvid_enc_create.num_zones = 0 /*NUM_ZONES*/;

	xvid_enc_create.plugins = plugins;
	xvid_enc_create.num_plugins = 0;

	/* changing default bitrate to 10Kb */
	{
		xvid_plugin_single_t single;
		memset(&single, 0, sizeof(xvid_plugin_single_t));
		single.version = XVID_VERSION;
		single.bitrate = 10000;

		plugins[xvid_enc_create.num_plugins].func = xvid_plugin_single;
		plugins[xvid_enc_create.num_plugins].param = &single;
		xvid_enc_create.num_plugins++;
	}
	/* end */

	/* No fancy thread tests */
	xvid_enc_create.num_threads = 0;

	if ((ARG_FRAMERATE - (int) ARG_FRAMERATE) < SMALL_EPS) {
		xvid_enc_create.fincr = 1;
		xvid_enc_create.fbase = (int) ARG_FRAMERATE;
	} else {
		xvid_enc_create.fincr = FRAMERATE_INCR;
		xvid_enc_create.fbase = (int) (FRAMERATE_INCR * ARG_FRAMERATE);
	}

	xvid_enc_create.max_key_interval = (int) ARG_FRAMERATE * 3 /*10*/;

	/* Bframes settings */
	xvid_enc_create.max_bframes = 0 /*ARG_MAXBFRAMES*/;
	xvid_enc_create.bquant_ratio = 150 /*ARG_BQRATIO*/;
	xvid_enc_create.bquant_offset = 100/*ARG_BQOFFSET*/;

	/* Dropping ratio frame -- we don't need that */
	xvid_enc_create.frame_drop_ratio = 0;

	/* Global encoder options */
	xvid_enc_create.global = 0;

	/* I use a small value here, since will not encode whole movies, but short clips */
	xerr = xvid_encore(NULL, XVID_ENC_CREATE, &xvid_enc_create, NULL);

	/* Retrieve the encoder instance from the structure */
	enc_handle = xvid_enc_create.handle;

	return (xerr);
}


int XVIDVideoCodec::enc_main(unsigned char *image,
		 unsigned char *bitstream,
		 int *key,
		 int *stats_type,
		 int *stats_quant,
		 int *stats_length,
		 int sse[3])
{
	int ret;

	xvid_enc_frame_t xvid_enc_frame;
	xvid_enc_stats_t xvid_enc_stats;

	/* Version for the frame and the stats */
	memset(&xvid_enc_frame, 0, sizeof(xvid_enc_frame));
	xvid_enc_frame.version = XVID_VERSION;

	memset(&xvid_enc_stats, 0, sizeof(xvid_enc_stats));
	xvid_enc_stats.version = XVID_VERSION;

	/* Bind output buffer */
	xvid_enc_frame.bitstream = bitstream;
	xvid_enc_frame.length = -1;

	/* Initialize input image fields */
	if (image) {
		xvid_enc_frame.input.plane[0] = image;
		xvid_enc_frame.input.csp = XVID_CSP_BGR;
		xvid_enc_frame.input.stride[0] = m_width*3;
	} else {
		xvid_enc_frame.input.csp = XVID_CSP_NULL;
	}

	/* Set up core's general features */
	xvid_enc_frame.vol_flags = 0;
	//xvid_enc_frame.vol_flags |= XVID_VOL_MPEGQUANT;

	/* Set up core's general features */
	xvid_enc_frame.vop_flags = vop_presets[ARG_QUALITY];

	/* Frame type -- let core decide for us */
	xvid_enc_frame.type = XVID_TYPE_AUTO;

	/* Force the right quantizer -- It is internally managed by RC plugins */
	xvid_enc_frame.quant = 0;

	/* Set up motion estimation flags */
	xvid_enc_frame.motion = motion_presets[ARG_QUALITY];

	/* We don't use special matrices */
	xvid_enc_frame.quant_intra_matrix = NULL;
	xvid_enc_frame.quant_inter_matrix = NULL;

	/* Encode the frame */
	ret = xvid_encore(enc_handle, XVID_ENC_ENCODE, &xvid_enc_frame,
					  &xvid_enc_stats);

	*key = (xvid_enc_frame.out_flags & XVID_KEYFRAME);
	*stats_type = xvid_enc_stats.type;
	*stats_quant = xvid_enc_stats.quant;
	*stats_length = xvid_enc_stats.length;
	sse[0] = xvid_enc_stats.sse_y;
	sse[1] = xvid_enc_stats.sse_u;
	sse[2] = xvid_enc_stats.sse_v;

	return (ret);
}

XVIDVideoCodec::XVIDVideoCodec()
{
	m_width = 160;
	m_height = 120;

	/* encoder init */
	in_buffer = (unsigned char *) malloc(m_width * m_height * 3);
	mp4_buffer = (unsigned char *) malloc(m_width * m_height * 3);

	ARG_FRAMERATE = 2.0f; /*25.00f;*/
	ARG_QUALITY = ME_ELEMENTS - 1;

	static void *enc_handle = NULL;

	int result = enc_init();
	if (result) {
		wxMessageBox("Could not initialize XVid CODEC", 
			_T("Error"),
			wxICON_ERROR | wxOK);
		exit(0);
	}


	/* decoder init */
	xvid_gbl_init_t   xvid_gbl_init;
	xvid_dec_create_t xvid_dec_create;

	/* Reset the structure with zeros */
	memset(&xvid_gbl_init, 0, sizeof(xvid_gbl_init_t));
	memset(&xvid_dec_create, 0, sizeof(xvid_dec_create_t));

	/* Version */
	xvid_gbl_init.version = XVID_VERSION;

	/* Assembly setting */
#ifdef ARCH_IS_IA64
		xvid_gbl_init.cpu_flags = XVID_CPU_FORCE | XVID_CPU_IA64;
#else
	xvid_gbl_init.cpu_flags = 0;
#endif

	xvid_gbl_init.debug = 0/*debug_level*/;

	xvid_global(NULL, 0, &xvid_gbl_init, NULL);

	xvid_dec_create.version = XVID_VERSION;
	xvid_dec_create.width = m_width;
	xvid_dec_create.height = m_height;
	int ret = xvid_decore(NULL, XVID_DEC_CREATE, &xvid_dec_create, NULL);
	if (ret) {
		wxMessageBox("Could not initialize XVid CODEC", 
			_T("Error"),
			wxICON_ERROR | wxOK);
		exit(0);
	}
	
	dec_handle = xvid_dec_create.handle;

	out_buffer = (unsigned char*)malloc(m_width*m_height*4);
	memset(out_buffer, 0x00, m_width*m_height*4);




}


XVIDVideoCodec::~XVIDVideoCodec()
{
	/* Destroy the encoder instance */
	xvid_encore(enc_handle, XVID_ENC_DESTROY, NULL, NULL);
}

unsigned char *XVIDVideoCodec::compressFrame(VideoFrame *input, int *len)
{
	int m4v_size;
	int key;
	int stats_type;
	int stats_quant;
	int stats_length;
	int sse[3];

	IplImage *original = input->getImage();
	IplImage *resized = cvCreateImage(cvSize(160, 120), IPL_DEPTH_8U, 3);

	cvResize(original, resized);

	memcpy(in_buffer, resized->imageData, resized->imageSize);
	m4v_size = enc_main(in_buffer, mp4_buffer, &key, &stats_type,
					 &stats_quant, &stats_length, sse);

	cvReleaseImage(&resized);

	if (m4v_size <= 0) {
		wxMessageBox("Could not compress with XVid CODEC", 
			_T("Error"),
			wxICON_ERROR | wxOK);
		return NULL;
	}

	unsigned char *retBuffer = new unsigned char[m4v_size];
	memcpy(retBuffer, mp4_buffer, m4v_size);
	*len = m4v_size;
	return retBuffer;
}

VideoFrame *XVIDVideoCodec::decompressFrame(unsigned char *vbuf, int buflen)
{
	xvid_dec_frame_t xvid_dec_frame;
	xvid_dec_stats_t xvid_dec_stats;


	int total = buflen;
	int ret;
	unsigned char *buffer_cur = vbuf;
	do {
		/* Reset all structures */
		memset(&xvid_dec_frame, 0, sizeof(xvid_dec_frame_t));
		memset(&xvid_dec_stats, 0, sizeof(xvid_dec_stats_t));

		/* Set version */
		xvid_dec_frame.version = XVID_VERSION;
		xvid_dec_stats.version = XVID_VERSION;

		/* No general flags to set */
		xvid_dec_frame.general          = 0;

		/* Input stream */
		xvid_dec_frame.bitstream        = buffer_cur;
		xvid_dec_frame.length           = total;

		/* Output frame structure */
		xvid_dec_frame.output.plane[0]  = out_buffer;
		xvid_dec_frame.output.stride[0] = m_width*3;
		xvid_dec_frame.output.csp = XVID_CSP_BGR;

		ret = xvid_decore(dec_handle, XVID_DEC_DECODE, &xvid_dec_frame, &xvid_dec_stats);
		total -= ret;
		buffer_cur += ret;
	} while( (total > 0) && (ret > 0));

	IplImage *decoded = cvCreateImage(cvSize(m_width, m_height), IPL_DEPTH_8U, 3);
	memcpy(decoded->imageData, out_buffer, decoded->imageSize);
	VideoFrame *frame = new VideoFrame(decoded);
	return frame;
}
