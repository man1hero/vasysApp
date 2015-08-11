package com.vasys.HC;

public class ErrorMsg {
	public static String getErrorMsg(int errorCode) {
		switch (errorCode) {
		case 0:
			return "[" + errorCode + " NET_DVR_NOERROR] û�д���";
		case 1:
			return "[" + errorCode
					+ " NET_DVR_PASSWORD_ERROR] �û����������ע��ʱ������û��������������";
		case 2:
			return "["
					+ errorCode
					+ " NET_DVR_NOENOUGHPRI] Ȩ�޲��㡣��ע���û�û��Ȩ��ִ�е�ǰ���豸�Ĳ�����������Զ���û������������Աȡ�";
		case 3:
			return "[" + errorCode + " NET_DVR_NOINIT] SDKδ��ʼ����";
		case 4:
			return "[" + errorCode
					+ " NET_DVR_CHANNEL_ERROR] ͨ���Ŵ����豸û�ж�Ӧ��ͨ���š�";
		case 5:
			return "[" + errorCode + " NET_DVR_OVER_MAXLINK] �豸�ܵ��������������";
		case 6:
			return "[" + errorCode
					+ " NET_DVR_VERSIONNOMATCH] �汾��ƥ�䡣SDK���豸�İ汾��ƥ�䡣";
		case 7:
			return "["
					+ errorCode
					+ " NET_DVR_NETWORK_FAIL_CONNECT] �����豸ʧ�ܡ��豸�����߻�����ԭ����������ӳ�ʱ�ȡ�";
		case 8:
			return "[" + errorCode + " NET_DVR_NETWORK_SEND_ERROR] ���豸����ʧ�ܡ�";
		case 9:
			return "[" + errorCode + " NET_DVR_NETWORK_RECV_ERROR] ���豸��������ʧ�ܡ�";
		case 10:
			return "[" + errorCode
					+ " NET_DVR_NETWORK_RECV_TIMEOUT] ���豸�������ݳ�ʱ��";
		case 11:
			return "["
					+ errorCode
					+ " NET_DVR_NETWORK_ERRORDATA] ���͵��������󡣷��͸��豸���ߴ��豸���յ������ݴ�����Զ�̲�������ʱ�����豸��֧�ֵ�ֵ��";
		case 12:
			return "[" + errorCode + " NET_DVR_ORDER_ERROR] ���ô������";
		case 13:
			return "[" + errorCode + " NET_DVR_OPERNOPERMIT] �޴�Ȩ�ޡ�";
		case 14:
			return "[" + errorCode + " NET_DVR_COMMANDTIMEOUT] �豸����ִ�г�ʱ��";
		case 15:
			return "[" + errorCode
					+ " NET_DVR_ERRORSERIALPORT] ���ںŴ���ָ�����豸���ںŲ����ڡ�";
		case 16:
			return "[" + errorCode
					+ " NET_DVR_ERRORALARMPORT] �����˿ڴ���ָ�����豸��������˿ڲ����ڡ�";
		case 17:
			return "["
					+ errorCode
					+ " NET_DVR_PARAMETER_ERROR] ��������SDK�ӿ��и����������������Ϊ�գ����߲�����ʽ��ֵ������Ҫ��";
		case 18:
			return "[" + errorCode + " NET_DVR_CHAN_EXCEPTION] �豸ͨ�����ڴ���״̬��";
		case 19:
			return "[" + errorCode
					+ " NET_DVR_NODISK] �豸��Ӳ�̡����豸��Ӳ��ʱ�����豸��¼���ļ���Ӳ�����õȲ���ʧ�ܡ�";
		case 20:
			return "["
					+ errorCode
					+ " NET_DVR_ERRORDISKNUM] Ӳ�̺Ŵ��󡣵����豸����Ӳ�̹������ʱ��ָ����Ӳ�̺Ų�����ʱ���ظô���";
		case 21:
			return "[" + errorCode + " NET_DVR_DISK_FULL] �豸Ӳ������";
		case 22:
			return "[" + errorCode + " NET_DVR_DISK_ERROR] �豸Ӳ�̳���";
		case 23:
			return "[" + errorCode + " NET_DVR_NOSUPPORT] �豸��֧�֡�";
		case 24:
			return "[" + errorCode + " NET_DVR_BUSY] �豸æ��";
		case 25:
			return "[" + errorCode + " NET_DVR_MODIFY_FAIL] �豸�޸Ĳ��ɹ���";
		case 26:
			return "[" + errorCode
					+ " NET_DVR_PASSWORD_FORMAT_ERROR] ���������ʽ����ȷ��";
		case 27:
			return "[" + errorCode + " NET_DVR_DISK_FORMATING] Ӳ�����ڸ�ʽ������������������";
		case 28:
			return "[" + errorCode + " NET_DVR_DVRNORESOURCE] �豸��Դ���㡣";
		case 29:
			return "[" + errorCode + " NET_DVR_DVROPRATEFAILED] �豸����ʧ�ܡ�";
		case 30:
			return "["
					+ errorCode
					+ " NET_DVR_OPENHOSTSOUND_FAIL] �����Խ��������㲥�����вɼ�������Ƶ�����Ƶ���ʧ�ܡ�";
		case 31:
			return "[" + errorCode + " NET_DVR_DVRVOICEOPENED] �豸�����Խ���ռ�á�";
		case 32:
			return "[" + errorCode + " NET_DVR_TIMEINPUTERROR] ʱ�����벻��ȷ��";
		case 33:
			return "[" + errorCode + " NET_DVR_NOSPECFILE] �ط�ʱ�豸û��ָ�����ļ���";
		case 34:
			return "["
					+ errorCode
					+ " NET_DVR_CREATEFILE_ERROR] �����ļ���������¼�񡢱���ͼƬ����ȡ�����ļ���Զ������¼��ʱ�����ļ�ʧ�ܡ�";
		case 35:
			return "["
					+ errorCode
					+ " NET_DVR_FILEOPENFAIL] ���ļ��������������ļ����豸�������ϴ���Ѷ�ļ�ʱ���ļ�ʧ�ܡ�";
		case 36:
			return "[" + errorCode + " NET_DVR_OPERNOTFINISH] �ϴεĲ�����û����ɡ�";
		case 37:
			return "[" + errorCode + " NET_DVR_GETPLAYTIMEFAIL] ��ȡ��ǰ���ŵ�ʱ�����";
		case 38:
			return "[" + errorCode + " NET_DVR_PLAYFAIL] ���ų���";
		case 39:
			return "[" + errorCode + " NET_DVR_FILEFORMAT_ERROR] �ļ���ʽ����ȷ��";
		case 40:
			return "[" + errorCode + " NET_DVR_DIR_ERROR] ·������";
		case 41:
			return "[" + errorCode
					+ " NET_DVR_ALLOC_RESOURCE_ERROR] SDK��Դ�������";
		case 42:
			return "["
					+ errorCode
					+ " NET_DVR_AUDIO_MODE_ERROR] ����ģʽ���󡣵�ǰ����������ģʽ��ʵ�����õ�ģʽ��������";
		case 43:
			return "[" + errorCode
					+ " NET_DVR_NOENOUGH_BUF] ������̫С�������豸���ݵĻ���������ͼƬ���������㡣";
		case 44:
			return "[" + errorCode + " NET_DVR_CREATESOCKET_ERROR] ����SOCKET����";
		case 45:
			return "[" + errorCode + " NET_DVR_SETSOCKET_ERROR] ����SOCKET����";
		case 46:
			return "[" + errorCode
					+ " NET_DVR_MAX_NUM] �����ﵽ��󡣷����ע����������Ԥ������������SDK֧�ֵ��������";
		case 47:
			return "[" + errorCode
					+ " NET_DVR_USERNOTEXIST] �û������ڡ�ע����û�ID��ע���򲻿��á�";
		case 48:
			return "[" + errorCode
					+ " NET_DVR_WRITEFLASHERROR] дFLASH�����豸����ʱдFLASHʧ�ܡ�";
		case 49:
			return "[" + errorCode
					+ " NET_DVR_UPGRADEFAIL] �豸����ʧ�ܡ�����������ļ����Բ�ƥ���ԭ������ʧ�ܡ�";
		case 50:
			return "[" + errorCode + " NET_DVR_CARDHAVEINIT] ���뿨�Ѿ���ʼ������";
		case 51:
			return "[" + errorCode + " NET_DVR_PLAYERFAILED] ���ò��ſ���ĳ������ʧ�ܡ�";
		case 52:
			return "[" + errorCode + " NET_DVR_MAX_USERNUM] ��¼�豸���û����ﵽ���";
		case 53:
			return "[" + errorCode
					+ " NET_DVR_GETLOCALIPANDMACFAIL] ��ñ���PC��IP��ַ�������ַʧ�ܡ�";
		case 54:
			return "[" + errorCode + " NET_DVR_NOENCODEING] �豸��ͨ��û���������롣";
		case 55:
			return "[" + errorCode + " NET_DVR_IPMISMATCH] IP��ַ��ƥ�䡣";
		case 56:
			return "[" + errorCode + " NET_DVR_MACMISMATCH] MAC��ַ��ƥ�䡣";
		case 57:
			return "[" + errorCode + " NET_DVR_UPGRADELANGMISMATCH] �����ļ����Բ�ƥ�䡣";
		case 58:
			return "[" + errorCode + " NET_DVR_MAX_PLAYERPORT] ������·���ﵽ���";
		case 59:
			return "[" + errorCode + " NET_DVR_NOSPACEBACKUP] �����豸��û���㹻�ռ���б��ݡ�";
		case 60:
			return "[" + errorCode + " NET_DVR_NODEVICEBACKUP] û���ҵ�ָ���ı����豸��";
		case 61:
			return "[" + errorCode
					+ " NET_DVR_PICTURE_BITS_ERROR] ͼ����λ����������24ɫ��";
		case 62:
			return "[" + errorCode
					+ " NET_DVR_PICTURE_DIMENSION_ERROR] ͼƬ��*���ޣ���128*256��";
		case 63:
			return "[" + errorCode
					+ " NET_DVR_PICTURE_SIZ_ERROR] ͼƬ��С���ޣ���100K��";
		case 64:
			return "[" + errorCode
					+ " NET_DVR_LOADPLAYERSDKFAILED] ���뵱ǰĿ¼��Player Sdk����";
		case 65:
			return "[" + errorCode
					+ " NET_DVR_LOADPLAYERSDKPROC_ERROR] �Ҳ���Player Sdk��ĳ��������ڡ�";
		case 66:
			return "[" + errorCode
					+ " NET_DVR_LOADDSSDKFAILED] ���뵱ǰĿ¼��DSsdk����";
		case 67:
			return "[" + errorCode
					+ " NET_DVR_LOADDSSDKPROC_ERROR] �Ҳ���DsSdk��ĳ��������ڡ�";
		case 68:
			return "[" + errorCode
					+ " NET_DVR_DSSDK_ERROR] ����Ӳ�����DsSdk��ĳ������ʧ�ܡ�";
		case 69:
			return "[" + errorCode + " NET_DVR_VOICEMONOPOLIZE] ��������ռ��";
		case 70:
			return "[" + errorCode + " NET_DVR_JOINMULTICASTFAILED] ����ಥ��ʧ�ܡ�";
		case 71:
			return "[" + errorCode + " NET_DVR_CREATEDIR_ERROR] ������־�ļ�Ŀ¼ʧ�ܡ�";
		case 72:
			return "[" + errorCode + " NET_DVR_BINDSOCKET_ERROR] ���׽���ʧ�ܡ�";
		case 73:
			return "["
					+ errorCode
					+ " NET_DVR_SOCKETCLOSE_ERROR] socket�����жϣ��˴���ͨ�������������жϻ�Ŀ�ĵز��ɴ";
		case 74:
			return "[" + errorCode + " NET_DVR_USERID_ISUSING] ע��ʱ�û�ID���ڽ���ĳ������";
		case 75:
			return "[" + errorCode + " NET_DVR_SOCKETLISTEN_ERROR] ����ʧ�ܡ�";
		case 76:
			return "[" + errorCode + " NET_DVR_PROGRAM_EXCEPTION] �����쳣��";
		case 77:
			return "["
					+ errorCode
					+ " NET_DVR_WRITEFILE_FAILED] д�ļ�ʧ�ܡ�����¼��Զ������¼������ͼƬ�Ȳ���ʱд�ļ�ʧ�ܡ�";
		case 78:
			return "[" + errorCode + " NET_DVR_FORMAT_READONLY] ��ֹ��ʽ��ֻ��Ӳ�̡�";
		case 79:
			return "[" + errorCode
					+ " NET_DVR_WITHSAMEUSERNAME] Զ���û����ýṹ�д�����ͬ���û�����";
		case 80:
			return "[" + errorCode + " NET_DVR_DEVICETYPE_ERROR] �������ʱ�豸�ͺŲ�ƥ�䡣";
		case 81:
			return "[" + errorCode + " NET_DVR_LANGUAGE_ERROR] �������ʱ���Բ�ƥ�䡣";
		case 82:
			return "[" + errorCode
					+ " NET_DVR_PARAVERSION_ERROR] �������ʱ����汾��ƥ�䡣";
		case 83:
			return "[" + errorCode + " NET_DVR_IPCHAN_NOTALIVE] Ԥ��ʱ���IPͨ�������ߡ�";
		case 84:
			return "[" + errorCode
					+ " NET_DVR_RTSP_SDK_ERROR] ���ر�׼Э��ͨѶ��StreamTransClientʧ�ܡ�";
		case 85:
			return "[" + errorCode + " NET_DVR_CONVERT_SDK_ERROR] ����ת��װ��ʧ�ܡ�";
		case 86:
			return "[" + errorCode
					+ " NET_DVR_IPC_COUNT_OVERFLOW] ��������IP����ͨ������";
		case 87:
			return "[" + errorCode
					+ " NET_DVR_MAX_ADD_NUM] ���¼���ǩ�������������������֧�ֵĸ�����";
		case 88:
			return "["
					+ errorCode
					+ " NET_DVR_PARAMMODE_ERROR] ͼ����ǿ�ǣ�����ģʽ��������Ӳ������ʱ���ͻ��˽����������ʱ����ֵ����";
		case 89:
			return "[" + errorCode + " NET_DVR_CODESPITTER_OFFLINE] ����������ߡ�";
		case 90:
			return "[" + errorCode + " NET_DVR_BACKUP_COPYING] �豸���ڱ��ݡ�";
		case 91:
			return "[" + errorCode + " NET_DVR_CHAN_NOTSUPPORT] ͨ����֧�ָò�����";
		case 92:
			return "[" + errorCode
					+ " NET_DVR_CALLINEINVALID] �߶���λ��̫���л򳤶��߲�����б��";
		case 93:
			return "[" + errorCode
					+ " NET_DVR_CALCANCELCONFLICT] ȡ���궨��ͻ����������˹���ȫ�ֵ�ʵ�ʴ�С�ߴ���ˡ�";
		case 94:
			return "[" + errorCode + " NET_DVR_CALPOINTOUTRANGE] �궨�㳬����Χ��";
		case 95:
			return "[" + errorCode + " NET_DVR_FILTERRECTINVALID] �ߴ������������Ҫ��";
		case 96:
			return "[" + errorCode + " NET_DVR_DDNS_DEVOFFLINE] �豸û��ע�ᵽddns�ϡ�";
		case 97:
			return "[" + errorCode + " NET_DVR_DDNS_INTER_ERROR] DDNS �������ڲ�����";
		case 99:
			return "[" + errorCode
					+ " NET_DVR_DEC_CHAN_REBIND] ����ͨ������ʾ����������ޡ�";
		case 150:
			return "[" + errorCode
					+ " NET_DVR_ALIAS_DUPLICATE] �����ظ���HiDDNS�����ã���";
		case 200:
			return "[" + errorCode + " NET_DVR_NAME_NOT_ONLY] �����Ѵ��ڡ�";
		case 201:
			return "[" + errorCode + " NET_DVR_OVER_MAX_ARRAY] ���дﵽ���ޡ�";
		case 202:
			return "[" + errorCode + " NET_DVR_OVER_MAX_VD] ������̴ﵽ���ޡ�";
		case 203:
			return "[" + errorCode + " NET_DVR_VD_SLOT_EXCEED] ������̲�λ������";
		case 204:
			return "[" + errorCode
					+ " NET_DVR_PD_STATUS_INVALID] �ؽ����������������״̬����";
		case 205:
			return "[" + errorCode
					+ " NET_DVR_PD_BE_DEDICATE_SPARE] �ؽ����������������Ϊָ���ȱ���";
		case 206:
			return "[" + errorCode + " NET_DVR_PD_NOT_FREE] �ؽ���������������̷ǿ��С�";
		case 207:
			return "[" + errorCode
					+ " NET_DVR_CANNOT_MIG2NEWMODE] ���ܴӵ�ǰ����������Ǩ�Ƶ��µ��������͡�";
		case 208:
			return "[" + errorCode + " NET_DVR_MIG_PAUSE] Ǩ�Ʋ�������ͣ��";
		case 209:
			return "[" + errorCode + " NET_DVR_MIG_CANCEL] ����ִ�е�Ǩ�Ʋ�����ȡ����";
		case 210:
			return "[" + errorCode + " NET_DVR_EXIST_VD] �����ϴ���������̣��޷�ɾ�����С�";
		case 211:
			return "[" + errorCode
					+ " NET_DVR_TARGET_IN_LD_FUNCTIONAL] �����������Ϊ���������ɲ����ҹ���������";
		case 212:
			return "[" + errorCode
					+ " NET_DVR_HD_IS_ASSIGNED_ALREADY] ָ����������̱�����Ϊ������̡�";
		case 213:
			return "[" + errorCode
					+ " NET_DVR_INVALID_HD_COUNT] �������������ָ����RAID�ȼ���ƥ�䡣";
		case 214:
			return "[" + errorCode + " NET_DVR_LD_IS_FUNCTIONAL] �����������޷��ؽ���";
		case 215:
			return "[" + errorCode + " NET_DVR_BGA_RUNNING] ��������ִ�еĺ�̨����";
		case 216:
			return "[" + errorCode + " NET_DVR_LD_NO_ATAPI] �޷���ATAPI�̴���������̡�";
		case 217:
			return "[" + errorCode + " NET_DVR_MIGRATION_NOT_NEED] ��������Ǩ�ơ�";
		case 218:
			return "[" + errorCode + " NET_DVR_HD_TYPE_MISMATCH] ������̲�����ͬ�����͡�";
		case 219:
			return "[" + errorCode + " NET_DVR_NO_LD_IN_DG] ��������̣��޷����д��������";
		case 220:
			return "[" + errorCode
					+ " NET_DVR_NO_ROOM_FOR_SPARE] ���̿ռ��С���޷���ָ��Ϊ�ȱ��̡�";
		case 221:
			return "[" + errorCode
					+ " NET_DVR_SPARE_IS_IN_MULTI_DG] �����ѱ�����Ϊĳ�����ȱ��̡�";
		case 222:
			return "[" + errorCode + " NET_DVR_DG_HAS_MISSING_PD] ����ȱ���̡�";
		case 223:
			return "[" + errorCode + " NET_DVR_NAME_EMPTY] ����Ϊ�ա�";
		case 224:
			return "[" + errorCode + " NET_DVR_INPUT_PARAM] �����������";
		case 225:
			return "[" + errorCode + " NET_DVR_PD_NOT_AVAILABLE] ������̲����á�";
		case 226:
			return "[" + errorCode + " NET_DVR_ARRAY_NOT_AVAILABLE] ���в����á�";
		case 227:
			return "[" + errorCode + " NET_DVR_PD_COUNT] �������������ȷ��";
		case 228:
			return "[" + errorCode + " NET_DVR_VD_SMALL] �������̫С��";
		case 229:
			return "[" + errorCode + " NET_DVR_NO_EXIST] �����ڡ�";
		case 230:
			return "[" + errorCode + " NET_DVR_NOT_SUPPORT] ��֧�ָò�����";
		case 231:
			return "[" + errorCode + " NET_DVR_NOT_FUNCTIONAL] ����״̬��������״̬��";
		case 232:
			return "[" + errorCode
					+ " NET_DVR_DEV_NODE_NOT_FOUND] ��������豸�ڵ㲻���ڡ�";
		case 233:
			return "[" + errorCode + " NET_DVR_SLOT_EXCEED] ��λ�ﵽ���ޡ�";
		case 234:
			return "[" + errorCode + " NET_DVR_NO_VD_IN_ARRAY] �����ϲ�����������̡�";
		case 235:
			return "[" + errorCode + " NET_DVR_VD_SLOT_INVALID] ������̲�λ��Ч��";
		case 236:
			return "[" + errorCode + " NET_DVR_PD_NO_ENOUGH_SPACE] ����������̿ռ䲻�㡣";
		case 237:
			return "[" + errorCode
					+ " NET_DVR_ARRAY_NONFUNCTION] ֻ�д�������״̬�����в��ܽ���Ǩ�ơ�";
		case 238:
			return "[" + errorCode + " NET_DVR_ARRAY_NO_ENOUGH_SPACE] ���пռ䲻�㡣";
		case 239:
			return "[" + errorCode
					+ " NET_DVR_STOPPING_SCANNING_ARRAY] ����ִ�а�ȫ���̻�����ɨ�衣";
		case 240:
			return "[" + errorCode + " NET_DVR_NOT_SUPPORT_16T] ��֧�ִ�������16T�����С�";
		case 300:
			return "[" + errorCode + " NET_DVR_ID_ERROR] ����ID������";
		case 301:
			return "[" + errorCode + " NET_DVR_POLYGON_ERROR] ����β�����Ҫ��";
		case 302:
			return "[" + errorCode + " NET_DVR_RULE_PARAM_ERROR] �������������";
		case 303:
			return "[" + errorCode + " NET_DVR_RULE_CFG_CONFLICT] ������Ϣ��ͻ��";
		case 304:
			return "[" + errorCode + " NET_DVR_CALIBRATE_NOT_READY] ��ǰû�б궨��Ϣ��";
		case 305:
			return "[" + errorCode + " NET_DVR_CAMERA_DATA_ERROR] ���������������";
		case 306:
			return "[" + errorCode
					+ " NET_DVR_CALIBRATE_DATA_UNFIT] ���Ȳ�����б�������ڱ궨��";
		case 307:
			return "[" + errorCode
					+ " NET_DVR_CALIBRATE_DATA_CONFILICT] �궨������Ϊ���е㹲�߻���λ��̫���С�";
		case 308:
			return "[" + errorCode
					+ " NET_DVR_CALIBRATE_CALC_FAIL] ������궨����ֵ����ʧ�ܡ�";
		case 309:
			return "[" + errorCode
					+ " NET_DVR_CALIBRATE_LINE_OUT_RECT] ����������궨�߳�����������Ӿ��ο�";
		case 310:
			return "[" + errorCode + " NET_DVR_ENTER_RULE_NOT_READY] û�����ý�������";
		case 311:
			return "["
					+ errorCode
					+ " NET_DVR_AID_RULE_NO_INCLUDE_LANE] ��ͨ�¼�������û�а�����������ֵӵ�º����У���";
		case 312:
			return "[" + errorCode + " NET_DVR_LANE_NOT_READY] ��ǰû�����ó�����";
		case 313:
			return "[" + errorCode
					+ " NET_DVR_RULE_INCLUDE_TWO_WAY] �¼������а���2�ֲ�ͬ����";
		case 314:
			return "[" + errorCode
					+ " NET_DVR_LANE_TPS_RULE_CONFLICT] ���������ݹ����ͻ��";
		case 315:
			return "[" + errorCode
					+ " NET_DVR_NOT_SUPPORT_EVENT_TYPE] ��֧�ֵ��¼����͡�";
		case 316:
			return "[" + errorCode + " NET_DVR_LANE_NO_WAY] ����û�з���";
		case 317:
			return "[" + errorCode + " NET_DVR_SIZE_FILTER_ERROR] �ߴ���˿򲻺���";
		case 318:
			return "[" + errorCode
					+ " NET_DVR_LIB_FFL_NO_FACE] �����㶨λʱ�����ͼ��û��������";
		case 319:
			return "[" + errorCode
					+ " NET_DVR_LIB_FFL_IMG_TOO_SMALL] �����㶨λʱ�����ͼ��̫С��";
		case 320:
			return "[" + errorCode
					+ " NET_DVR_LIB_FD_IMG_NO_FACE] ����ͼ���������ʱ�����ͼ��û��������";
		case 321:
			return "[" + errorCode + " NET_DVR_LIB_FACE_TOO_SMALL] ��ģʱ����̫С��";
		case 322:
			return "[" + errorCode
					+ " NET_DVR_LIB_FACE_QUALITY_TOO_BAD] ��ģʱ����ͼ������̫�";
		case 323:
			return "[" + errorCode + " NET_DVR_KEY_PARAM_ERR] �߼��������ô���";
		case 324:
			return "[" + errorCode
					+ " NET_DVR_CALIBRATE_DATA_ERR] �궨������Ŀ���󣬻�����ֵ���󣬻������㳬����ƽ�ߡ�";
		case 325:
			return "[" + errorCode
					+ " NET_DVR_CALIBRATE_DISABLE_FAIL] �����ù�������ȡ���궨��";
		case 800:
			return "[" + errorCode + " NET_DVR_DEV_NET_OVERFLOW] �������������豸�������ޡ�";
		case 801:
			return "["
					+ errorCode
					+ " NET_DVR_STATUS_RECORDFILE_WRITING_NOT_LOCK] ¼���ļ���¼���޷���������";
		case 802:
			return "[" + errorCode
					+ " NET_DVR_STATUS_CANT_FORMAT_LITTLE_DISK] ����Ӳ��̫С�޷���ʽ����";
		case 901:
			return "[" + errorCode + " NET_ERR_WINCHAN_IDX] ����ͨ���Ŵ���";
		case 902:
			return "[" + errorCode
					+ " NET_ERR_WIN_LAYER] ���ڲ������󣬵�����Ļ����า�ǵĴ��ڲ�����";
		case 903:
			return "[" + errorCode
					+ " NET_ERR_WIN_BLK_NUM] ���ڵĿ������󣬵������ڿɸ��ǵ���Ļ������";
		case 904:
			return "[" + errorCode + " NET_ERR_OUTPUT_RESOLUTION] ����ֱ��ʴ���";
		case 905:
			return "[" + errorCode + " NET_ERR_LAYOUT] ���ֺŴ���";
		case 906:
			return "[" + errorCode + " NET_ERR_INPUT_RESOLUTION] ����ֱ��ʲ�֧�֡�";
		case 907:
			return "[" + errorCode + " NET_ERR_SUBDEVICE_OFFLINE] ���豸�����ߡ�";
		case 908:
			return "[" + errorCode + " NET_ERR_NO_DECODE_CHAN] û�п��н���ͨ����";
		case 909:
			return "[" + errorCode + " NET_ERR_MAX_WINDOW_ABILITY] �����������ޡ�";
		case 910:
			return "[" + errorCode + " NET_ERR_ORDER_ERROR] ����˳������";
		case 911:
			return "[" + errorCode + " NET_ERR_PLAYING_PLAN] ����ִ��Ԥ����";
		case 912:
			return "[" + errorCode + " NET_ERR_DECODER_USED] ���������ʹ�á�";
		case 401:
			return "[" + errorCode
					+ " NET_DVR_RTSP_ERROR_NOENOUGHPRI] ��Ȩ�ޣ�����������401ʱ��ת����������롣";
		case 402:
			return "[" + errorCode
					+ " NET_DVR_RTSP_ERROR_ALLOC_RESOURCE] ������Դʧ�ܡ�";
		case 403:
			return "[" + errorCode + " NET_DVR_RTSP_ERROR_PARAMETER] ��������";
		case 404:
			return "["
					+ errorCode
					+ " NET_DVR_RTSP_ERROR_NO_URL] ָ����URL��ַ�����ڣ�����������404ʱ��ת����������롣";
		case 406:
			return "[" + errorCode
					+ " NET_DVR_RTSP_ERROR_FORCE_STOP] �û���;ǿ���˳���";
		case 407:
			return "[" + errorCode + " NET_DVR_RTSP_GETPORTFAILED] ��ȡRTSP�˿ڴ���";
		case 410:
			return "[" + errorCode
					+ " NET_DVR_RTSP_DESCRIBERROR] RTSP DECRIBE��������";
		case 411:
			return "[" + errorCode
					+ " NET_DVR_RTSP_DESCRIBESENDTIMEOUT] RTSP DECRIBE���ͳ�ʱ��";
		case 412:
			return "[" + errorCode
					+ " NET_DVR_RTSP_DESCRIBESENDERROR] RTSP DECRIBE����ʧ�ܡ�";
		case 413:
			return "[" + errorCode
					+ " NET_DVR_RTSP_DESCRIBERECVTIMEOUT] RTSP DECRIBE���ճ�ʱ��";
		case 414:
			return "[" + errorCode
					+ " NET_DVR_RTSP_DESCRIBERECVDATALOST] RTSP DECRIBE�������ݴ���";
		case 415:
			return "[" + errorCode
					+ " NET_DVR_RTSP_DESCRIBERECVERROR] RTSP DECRIBE����ʧ�ܡ�";
		case 416:
			return "["
					+ errorCode
					+ " NET_DVR_RTSP_DESCRIBESERVERERR] RTSP DECRIBE����������401,501�ȴ���";
		case 420:
			return "[" + errorCode
					+ " NET_DVR_RTSP_SETUPERROR] RTSP SETUP��������";
		case 421:
			return "[" + errorCode
					+ " NET_DVR_RTSP_SETUPSENDTIMEOUT] RTSP SETUP���ͳ�ʱ��";
		case 422:
			return "[" + errorCode
					+ " NET_DVR_RTSP_SETUPSENDERROR] RTSP SETUP���ʹ���";
		case 423:
			return "[" + errorCode
					+ " NET_DVR_RTSP_SETUPRECVTIMEOUT] RTSP SETUP���ճ�ʱ��";
		case 424:
			return "[" + errorCode
					+ " NET_DVR_RTSP_SETUPRECVDATALOST] RTSP SETUP�������ݴ���";
		case 425:
			return "[" + errorCode
					+ " NET_DVR_RTSP_SETUPRECVERROR] RTSP SETUP����ʧ�ܡ�";
		case 426:
			return "[" + errorCode + " NET_DVR_RTSP_OVER_MAX_CHAN] �豸���������������";
		case 430:
			return "[" + errorCode + " NET_DVR_RTSP_PLAYERROR] RTSP PLAY��������";
		case 431:
			return "[" + errorCode
					+ " NET_DVR_RTSP_PLAYSENDTIMEOUT] RTSP PLAY���ͳ�ʱ��";
		case 432:
			return "[" + errorCode
					+ " NET_DVR_RTSP_PLAYSENDERROR] RTSP PLAY���ʹ���";
		case 433:
			return "[" + errorCode
					+ " NET_DVR_RTSP_PLAYRECVTIMEOUT] RTSP PLAT���ճ�ʱ��";
		case 434:
			return "[" + errorCode
					+ " NET_DVR_RTSP_PLAYRECVDATALOST] RTSP PLAY�������ݴ���";
		case 435:
			return "[" + errorCode
					+ " NET_DVR_RTSP_PLAYRECVERROR] RTSP PLAY����ʧ�ܡ�";
		case 436:
			return "[" + errorCode
					+ " NET_DVR_RTSP_PLAYSERVERERR] RTSP PLAY�豸���ش���״̬��";
		case 440:
			return "[" + errorCode
					+ " NET_DVR_RTSP_TEARDOWNERROR] RTSP TEARDOWN��������";
		case 441:
			return "[" + errorCode
					+ " NET_DVR_RTSP_TEARDOWNSENDTIMEOUT] RTSP TEARDOWN���ͳ�ʱ��";
		case 442:
			return "[" + errorCode
					+ " NET_DVR_RTSP_TEARDOWNSENDERROR] RTSP TEARDOWN���ʹ���";
		case 443:
			return "[" + errorCode
					+ " NET_DVR_RTSP_TEARDOWNRECVTIMEOUT] RTSP TEARDOWN���ճ�ʱ��";
		case 444:
			return "["
					+ errorCode
					+ " NET_DVR_RTSP_TEARDOWNRECVDATALOST] RTSP TEARDOWN�������ݴ���";
		case 445:
			return "[" + errorCode
					+ " NET_DVR_RTSP_TEARDOWNRECVERROR] RTSP TEARDOWN����ʧ�ܡ�";
		case 446:
			return "[" + errorCode
					+ " NET_DVR_RTSP_TEARDOWNSERVERERR] RTSP TEARDOWN�豸���ش���״̬��";
		case 500:
			return "[" + errorCode + " NET_PLAYM4_NOERROR] û�д���";
		case 501:
			return "[" + errorCode + " NET_PLAYM4_PARA_OVER] ��������Ƿ���";
		case 502:
			return "[" + errorCode + " NET_PLAYM4_ORDER_ERROR] ����˳�򲻶ԡ�";
		case 503:
			return "[" + errorCode + " NET_PLAYM4_TIMER_ERROR] ��ý��ʱ������ʧ�ܡ�";
		case 504:
			return "[" + errorCode + " NET_PLAYM4_DEC_VIDEO_ERROR] ��Ƶ����ʧ�ܡ�";
		case 505:
			return "[" + errorCode + " NET_PLAYM4_DEC_AUDIO_ERROR] ��Ƶ����ʧ�ܡ�";
		case 506:
			return "[" + errorCode + " NET_PLAYM4_ALLOC_MEMORY_ERROR] �����ڴ�ʧ�ܡ�";
		case 507:
			return "[" + errorCode + " NET_PLAYM4_OPEN_FILE_ERROR] �ļ�����ʧ�ܡ�";
		case 508:
			return "[" + errorCode + " NET_PLAYM4_CREATE_OBJ_ERROR] �����߳��¼���ʧ�ܡ�";
		case 509:
			return "[" + errorCode
					+ " NET_PLAYM4_CREATE_DDRAW_ERROR] ����directDrawʧ�ܡ�";
		case 510:
			return "[" + errorCode
					+ " NET_PLAYM4_CREATE_OFFSCREEN_ERROR] ������˻���ʧ�ܡ�";
		case 511:
			return "[" + errorCode + " NET_PLAYM4_BUF_OVER] ����������������ʧ�ܡ�";
		case 512:
			return "[" + errorCode
					+ " NET_PLAYM4_CREATE_SOUND_ERROR] ������Ƶ�豸ʧ�ܡ�";
		case 513:
			return "[" + errorCode + " NET_PLAYM4_SET_VOLUME_ERROR] ��������ʧ�ܡ�";
		case 514:
			return "[" + errorCode
					+ " NET_PLAYM4_SUPPORT_FILE_ONLY] ֻ���ڲ����ļ�ʱ����ʹ�ô˽ӿڡ�";
		case 515:
			return "[" + errorCode
					+ " NET_PLAYM4_SUPPORT_STREAM_ONLY] ֻ���ڲ�����ʱ����ʹ�ô˽ӿڡ�";
		case 516:
			return "[" + errorCode
					+ " NET_PLAYM4_SYS_NOT_SUPPORT] ϵͳ��֧�֣�������ֻ�ܹ�����Pentium";
		case 517:
			return "[" + errorCode + " NET_PLAYM4_FILEHEADER_UNKNOWN] û���ļ�ͷ��";
		case 518:
			return "[" + errorCode
					+ " NET_PLAYM4_VERSION_INCORRECT] �������ͱ������汾����Ӧ��";
		case 519:
			return "[" + errorCode
					+ " NET_PALYM4_INIT_DECODER_ERROR] ��ʼ��������ʧ�ܡ�";
		case 520:
			return "[" + errorCode
					+ " NET_PLAYM4_CHECK_FILE_ERROR] �ļ�̫�̻������޷�ʶ��";
		case 521:
			return "[" + errorCode
					+ " NET_PLAYM4_INIT_TIMER_ERROR] ��ʼ����ý��ʱ��ʧ�ܡ�";
		case 522:
			return "[" + errorCode + " NET_PLAYM4_BLT_ERROR] λ����ʧ�ܡ�";
		case 523:
			return "[" + errorCode + " NET_PLAYM4_UPDATE_ERROR] ��ʾoverlayʧ�ܡ�";
		case 524:
			return "[" + errorCode
					+ " NET_PLAYM4_OPEN_FILE_ERROR_MULTI] �򿪻�����ļ�ʧ�ܡ�";
		case 525:
			return "[" + errorCode
					+ " NET_PLAYM4_OPEN_FILE_ERROR_VIDEO] ����Ƶ���ļ�ʧ�ܡ�";
		case 526:
			return "[" + errorCode
					+ " NET_PLAYM4_JPEG_COMPRESS_ERROR] JPEGѹ������";
		case 527:
			return "[" + errorCode
					+ " NET_PLAYM4_EXTRACT_NOT_SUPPORT] ��֧�ָ��ļ��汾��";
		case 528:
			return "[" + errorCode
					+ " NET_PLAYM4_EXTRACT_DATA_ERROR] ��ȡ�ļ�����ʧ�ܡ�";
		case 678:
			return "["
					+ errorCode
					+ " NET_QOS_ERR_SCHEDPARAMS_BAD_MINIMUM_INTERVAL] Ԥ�����С�������";
		case 679:
			return "[" + errorCode
					+ " NET_QOS_ERR_SCHEDPARAMS_BAD_FRACTION] Ԥ���������";
		case 680:
			return "[" + errorCode
					+ " NET_QOS_ERR_SCHEDPARAMS_INVALID_BANDWIDTH] Ԥ��Ĵ���ֵ��Ч��";
		case 687:
			return "[" + errorCode + " NET_QOS_ERR_PACKET_TOO_BIG] ���ݰ�̫��";
		case 688:
			return "[" + errorCode + " NET_QOS_ERR_PACKET_LENGTH] ���ݰ����ȴ���";
		case 689:
			return "[" + errorCode + " NET_QOS_ERR_PACKET_VERSION] ���ݰ��汾����";
		case 690:
			return "[" + errorCode + " NET_QOS_ERR_PACKET_UNKNOW] δ֪���ݰ���";
		case 695:
			return "[" + errorCode + " NET_QOS_ERR_OUTOFMEM] �ڴ治�㡣";
		case 696:
			return "[" + errorCode
					+ " NET_QOS_ERR_LIB_NOT_INITIALIZED] Lib��û�г�ʼ����";
		case 697:
			return "[" + errorCode + " NET_QOS_ERR_SESSION_NOT_FOUND] û���ҵ��Ự��";
		case 698:
			return "[" + errorCode + " NET_QOS_ERR_INVALID_ARGUMENTS] ������Ч��";
		case 699:
			return "[" + errorCode + " NET_QOS_ERROR] Qos ����";
		case 700:
			return "[" + errorCode + " NET_QOS_OK] û�д���";
		default:
			return "[" + errorCode + " NET_???_??] δ֪����";
		}
	}

}