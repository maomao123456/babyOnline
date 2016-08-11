package com.example.lei;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	// �ж��ֻ������Ƿ���ȷ
	public static boolean isRightPhone(String phoneNumber) {
		/**
		 * �����ֻ�������֤����
		 * �ƶ���134��135��136��137��138��139��150��151��157(TD)��158��159��187��188
		 * ��ͨ��130��131��132��152��155��156��185��186 ���ţ�133��153��180��189����1349��ͨ��
		 * ������4G�ֻ��ŶΣ� �й����ŷֵ��ºŶ�170,177,��ͨ�ֵ���176,�ƶ��ֵ���178�Ŷ�.
		 */
		// �жϵ绰�����Ƿ���ϸ�ʽ
		String regExp = "^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0,6-8])|(18[0-9]))\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(phoneNumber);
		return m.matches();
	}
}
