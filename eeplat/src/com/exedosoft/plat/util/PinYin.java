package com.exedosoft.plat.util;

import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

public class PinYin {
	private static Hashtable ht = new Hashtable(10);

	private static String g(Integer n) {
		int num = n.intValue();
		if (num > 0 && num < 160) {
			return String.valueOf((char) num);
		} else if (num < -20319 || num > -10247) {
			return "";
		} else {
			if (ht.size() == 0) {
				b();
			}
			while (!ht.containsKey(Integer.toString(num)))
				num--;
			return ht.get(Integer.toString(num)).toString();
		}
	}

	public static String getPingyin(String str) {

		String ret = str;
		try {
			str = new String(str.getBytes(), "ISO-8859-1");
			char[] hz = str.toCharArray();
			// byte[] hz=str.getBytes() ;
			int len = str.length();
			// int len=hz.length;
			int p, q;
			ret = "";
			for (int i = 0; i < len; i++) {
				p = (int) hz[i];
				if (p > 160) {
					q = (int) hz[++i];
					p = p * 256 + q - 65536;
				}
				ret += g(new Integer(p));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			str = Escape.escape(str);
			// e.printStackTrace();
		}
		return ret;
	}

	private static void b() {
		ht.put("-20319", "a");
		ht.put("-20317", "ai");
		ht.put("-20304", "an");
		ht.put("-20295", "ang");
		ht.put("-20292", "ao");
		ht.put("-20283", "ba");
		ht.put("-20265", "bai");
		ht.put("-20257", "ban");
		ht.put("-20242", "bang");
		ht.put("-20230", "bao");
		ht.put("-20051", "bei");
		ht.put("-20036", "ben");
		ht.put("-20032", "beng");
		ht.put("-20026", "bi");
		ht.put("-20002", "bian");
		ht.put("-19990", "biao");
		ht.put("-19986", "bie");
		ht.put("-19982", "bin");
		ht.put("-19976", "bing");
		ht.put("-19805", "bo");
		ht.put("-19784", "bu");
		ht.put("-19775", "ca");
		ht.put("-19774", "cai");
		ht.put("-19763", "can");
		ht.put("-19756", "cang");
		ht.put("-19751", "cao");
		ht.put("-19746", "ce");
		ht.put("-19741", "ceng");
		ht.put("-19739", "cha");
		ht.put("-19728", "chai");
		ht.put("-19725", "chan");
		ht.put("-19715", "chang");
		ht.put("-19540", "chao");
		ht.put("-19531", "che");
		ht.put("-19525", "chen");
		ht.put("-19515", "cheng");
		ht.put("-19500", "chi");
		ht.put("-19484", "chong");
		ht.put("-19479", "chou");
		ht.put("-19467", "chu");
		ht.put("-19289", "chuai");
		ht.put("-19288", "chuan");
		ht.put("-19281", "chuang");
		ht.put("-19275", "chui");
		ht.put("-19270", "chun");
		ht.put("-19263", "chuo");
		ht.put("-19261", "ci");
		ht.put("-19249", "cong");
		ht.put("-19243", "cou");
		ht.put("-19242", "cu");
		ht.put("-19238", "cuan");
		ht.put("-19235", "cui");
		ht.put("-19227", "cun");
		ht.put("-19224", "cuo");
		ht.put("-19218", "da");
		ht.put("-19212", "dai");
		ht.put("-19038", "dan");
		ht.put("-19023", "dang");
		ht.put("-19018", "dao");
		ht.put("-19006", "de");
		ht.put("-19003", "deng");
		ht.put("-18996", "di");
		ht.put("-18977", "dian");
		ht.put("-18961", "diao");
		ht.put("-18952", "die");
		ht.put("-18783", "ding");
		ht.put("-18774", "diu");
		ht.put("-18773", "dong");
		ht.put("-18763", "dou");
		ht.put("-18756", "du");
		ht.put("-18741", "duan");
		ht.put("-18735", "dui");
		ht.put("-18731", "dun");
		ht.put("-18722", "duo");
		ht.put("-18710", "e");
		ht.put("-18697", "en");
		ht.put("-18696", "er");
		ht.put("-18526", "fa");
		ht.put("-18518", "fan");
		ht.put("-18501", "fang");
		ht.put("-18490", "fei");
		ht.put("-18478", "fen");
		ht.put("-18463", "feng");
		ht.put("-18448", "fo");
		ht.put("-18447", "fou");
		ht.put("-18446", "fu");
		ht.put("-18239", "ga");
		ht.put("-18237", "gai");
		ht.put("-18231", "gan");
		ht.put("-18220", "gang");
		ht.put("-18211", "gao");
		ht.put("-18201", "ge");
		ht.put("-18184", "gei");
		ht.put("-18183", "gen");
		ht.put("-18181", "geng");
		ht.put("-18012", "gong");
		ht.put("-17997", "gou");
		ht.put("-17988", "gu");
		ht.put("-17970", "gua");
		ht.put("-17964", "guai");
		ht.put("-17961", "guan");
		ht.put("-17950", "guang");
		ht.put("-17947", "gui");
		ht.put("-17931", "gun");
		ht.put("-17928", "guo");
		ht.put("-17922", "ha");
		ht.put("-17759", "hai");
		ht.put("-17752", "han");
		ht.put("-17733", "hang");
		ht.put("-17730", "hao");
		ht.put("-17721", "he");
		ht.put("-17703", "hei");
		ht.put("-17701", "hen");
		ht.put("-17697", "heng");
		ht.put("-17692", "hong");
		ht.put("-17683", "hou");
		ht.put("-17676", "hu");
		ht.put("-17496", "hua");
		ht.put("-17487", "huai");
		ht.put("-17482", "huan");
		ht.put("-17468", "huang");
		ht.put("-17454", "hui");
		ht.put("-17433", "hun");
		ht.put("-17427", "huo");
		ht.put("-17417", "ji");
		ht.put("-17202", "jia");
		ht.put("-17185", "jian");
		ht.put("-16983", "jiang");
		ht.put("-16970", "jiao");
		ht.put("-16942", "jie");
		ht.put("-16915", "jin");
		ht.put("-16733", "jing");
		ht.put("-16708", "jiong");
		ht.put("-16706", "jiu");
		ht.put("-16689", "ju");
		ht.put("-16664", "juan");
		ht.put("-16657", "jue");
		ht.put("-16647", "jun");
		ht.put("-16474", "ka");
		ht.put("-16470", "kai");
		ht.put("-16465", "kan");
		ht.put("-16459", "kang");
		ht.put("-16452", "kao");
		ht.put("-16448", "ke");
		ht.put("-16433", "ken");
		ht.put("-16429", "keng");
		ht.put("-16427", "kong");
		ht.put("-16423", "kou");
		ht.put("-16419", "ku");
		ht.put("-16412", "kua");
		ht.put("-16407", "kuai");
		ht.put("-16403", "kuan");
		ht.put("-16401", "kuang");
		ht.put("-16393", "kui");
		ht.put("-16220", "kun");
		ht.put("-16216", "kuo");
		ht.put("-16212", "la");
		ht.put("-16205", "lai");
		ht.put("-16202", "lan");
		ht.put("-16187", "lang");
		ht.put("-16180", "lao");
		ht.put("-16171", "le");
		ht.put("-16169", "lei");
		ht.put("-16158", "leng");
		ht.put("-16155", "li");
		ht.put("-15959", "lia");
		ht.put("-15958", "lian");
		ht.put("-15944", "liang");
		ht.put("-15933", "liao");
		ht.put("-15920", "lie");
		ht.put("-15915", "lin");
		ht.put("-15903", "ling");
		ht.put("-15889", "liu");
		ht.put("-15878", "long");
		ht.put("-15707", "lou");
		ht.put("-15701", "lu");
		ht.put("-15681", "lv");
		ht.put("-15667", "luan");
		ht.put("-15661", "lue");
		ht.put("-15659", "lun");
		ht.put("-15652", "luo");
		ht.put("-15640", "ma");
		ht.put("-15631", "mai");
		ht.put("-15625", "man");
		ht.put("-15454", "mang");
		ht.put("-15448", "mao");
		ht.put("-15436", "me");
		ht.put("-15435", "mei");
		ht.put("-15419", "men");
		ht.put("-15416", "meng");
		ht.put("-15408", "mi");
		ht.put("-15394", "mian");
		ht.put("-15385", "miao");
		ht.put("-15377", "mie");
		ht.put("-15375", "min");
		ht.put("-15369", "ming");
		ht.put("-15363", "miu");
		ht.put("-15362", "mo");
		ht.put("-15183", "mou");
		ht.put("-15180", "mu");
		ht.put("-15165", "na");
		ht.put("-15158", "nai");
		ht.put("-15153", "nan");
		ht.put("-15150", "nang");
		ht.put("-15149", "nao");
		ht.put("-15144", "ne");
		ht.put("-15143", "nei");
		ht.put("-15141", "nen");
		ht.put("-15140", "neng");
		ht.put("-15139", "ni");
		ht.put("-15128", "nian");
		ht.put("-15121", "niang");
		ht.put("-15119", "niao");
		ht.put("-15117", "nie");
		ht.put("-15110", "nin");
		ht.put("-15109", "ning");
		ht.put("-14941", "niu");
		ht.put("-14937", "nong");
		ht.put("-14933", "nu");
		ht.put("-14930", "nv");
		ht.put("-14929", "nuan");
		ht.put("-14928", "nue");
		ht.put("-14926", "nuo");
		ht.put("-14922", "o");
		ht.put("-14921", "ou");
		ht.put("-14914", "pa");
		ht.put("-14908", "pai");
		ht.put("-14902", "pan");
		ht.put("-14894", "pang");
		ht.put("-14889", "pao");
		ht.put("-14882", "pei");
		ht.put("-14873", "pen");
		ht.put("-14871", "peng");
		ht.put("-14857", "pi");
		ht.put("-14678", "pian");
		ht.put("-14674", "piao");
		ht.put("-14670", "pie");
		ht.put("-14668", "pin");
		ht.put("-14663", "ping");
		ht.put("-14654", "po");
		ht.put("-14645", "pu");
		ht.put("-14630", "qi");
		ht.put("-14594", "qia");
		ht.put("-14429", "qian");
		ht.put("-14407", "qiang");
		ht.put("-14399", "qiao");
		ht.put("-14384", "qie");
		ht.put("-14379", "qin");
		ht.put("-14368", "qing");
		ht.put("-14355", "qiong");
		ht.put("-14353", "qiu");
		ht.put("-14345", "qu");
		ht.put("-14170", "quan");
		ht.put("-14159", "que");
		ht.put("-14151", "qun");
		ht.put("-14149", "ran");
		ht.put("-14145", "rang");
		ht.put("-14140", "rao");
		ht.put("-14137", "re");
		ht.put("-14135", "ren");
		ht.put("-14125", "reng");
		ht.put("-14123", "ri");
		ht.put("-14122", "rong");
		ht.put("-14112", "rou");
		ht.put("-14109", "ru");
		ht.put("-14099", "ruan");
		ht.put("-14097", "rui");
		ht.put("-14094", "run");
		ht.put("-14092", "ruo");
		ht.put("-14090", "sa");
		ht.put("-14087", "sai");
		ht.put("-14083", "san");
		ht.put("-13917", "sang");
		ht.put("-13914", "sao");
		ht.put("-13910", "se");
		ht.put("-13907", "sen");
		ht.put("-13906", "seng");
		ht.put("-13905", "sha");
		ht.put("-13896", "shai");
		ht.put("-13894", "shan");
		ht.put("-13878", "shang");
		ht.put("-13870", "shao");
		ht.put("-13859", "she");
		ht.put("-13847", "shen");
		ht.put("-13831", "sheng");
		ht.put("-13658", "shi");
		ht.put("-13611", "shou");
		ht.put("-13601", "shu");
		ht.put("-13406", "shua");
		ht.put("-13404", "shuai");
		ht.put("-13400", "shuan");
		ht.put("-13398", "shuang");
		ht.put("-13395", "shui");
		ht.put("-13391", "shun");
		ht.put("-13387", "shuo");
		ht.put("-13383", "si");
		ht.put("-13367", "song");
		ht.put("-13359", "sou");
		ht.put("-13356", "su");
		ht.put("-13343", "suan");
		ht.put("-13340", "sui");
		ht.put("-13329", "sun");
		ht.put("-13326", "suo");
		ht.put("-13318", "ta");
		ht.put("-13147", "tai");
		ht.put("-13138", "tan");
		ht.put("-13120", "tang");
		ht.put("-13107", "tao");
		ht.put("-13096", "te");
		ht.put("-13095", "teng");
		ht.put("-13091", "ti");
		ht.put("-13076", "tian");
		ht.put("-13068", "tiao");
		ht.put("-13063", "tie");
		ht.put("-13060", "ting");
		ht.put("-12888", "tong");
		ht.put("-12875", "tou");
		ht.put("-12871", "tu");
		ht.put("-12860", "tuan");
		ht.put("-12858", "tui");
		ht.put("-12852", "tun");
		ht.put("-12849", "tuo");
		ht.put("-12838", "wa");
		ht.put("-12831", "wai");
		ht.put("-12829", "wan");
		ht.put("-12812", "wang");
		ht.put("-12802", "wei");
		ht.put("-12607", "wen");
		ht.put("-12597", "weng");
		ht.put("-12594", "wo");
		ht.put("-12585", "wu");
		ht.put("-12556", "xi");
		ht.put("-12359", "xia");
		ht.put("-12346", "xian");
		ht.put("-12320", "xiang");
		ht.put("-12300", "xiao");
		ht.put("-12120", "xie");
		ht.put("-12099", "xin");
		ht.put("-12089", "xing");
		ht.put("-12074", "xiong");
		ht.put("-12067", "xiu");
		ht.put("-12058", "xu");
		ht.put("-12039", "xuan");
		ht.put("-11867", "xue");
		ht.put("-11861", "xun");
		ht.put("-11847", "ya");
		ht.put("-11831", "yan");
		ht.put("-11798", "yang");
		ht.put("-11781", "yao");
		ht.put("-11604", "ye");
		ht.put("-11589", "yi");
		ht.put("-11536", "yin");
		ht.put("-11358", "ying");
		ht.put("-11340", "yo");
		ht.put("-11339", "yong");
		ht.put("-11324", "you");
		ht.put("-11303", "yu");
		ht.put("-11097", "yuan");
		ht.put("-11077", "yue");
		ht.put("-11067", "yun");
		ht.put("-11055", "za");
		ht.put("-11052", "zai");
		ht.put("-11045", "zan");
		ht.put("-11041", "zang");
		ht.put("-11038", "zao");
		ht.put("-11024", "ze");
		ht.put("-11020", "zei");
		ht.put("-11019", "zen");
		ht.put("-11018", "zeng");
		ht.put("-11014", "zha");
		ht.put("-10838", "zhai");
		ht.put("-10832", "zhan");
		ht.put("-10815", "zhang");
		ht.put("-10800", "zhao");
		ht.put("-10790", "zhe");
		ht.put("-10780", "zhen");
		ht.put("-10764", "zheng");
		ht.put("-10587", "zhi");
		ht.put("-10544", "zhong");
		ht.put("-10533", "zhou");
		ht.put("-10519", "zhu");
		ht.put("-10331", "zhua");
		ht.put("-10329", "zhuai");
		ht.put("-10328", "zhuan");
		ht.put("-10322", "zhuang");
		ht.put("-10315", "zhui");
		ht.put("-10309", "zhun");
		ht.put("-10307", "zhuo");
		ht.put("-10296", "zi");
		ht.put("-10281", "zong");
		ht.put("-10274", "zou");
		ht.put("-10270", "zu");
		ht.put("-10262", "zuan");
		ht.put("-10260", "zui");
		ht.put("-10256", "zun");
		ht.put("-10254", "zuo");
		ht.put("-10247", "zz");
	}

	public static String getPingyinSM(String str) {
		
		if(str==null){
			return "";
		}

		char[] chars = str.toCharArray();
		StringBuilder sm = new StringBuilder("");
		for (int i = 0; i < chars.length; i++) {
			char a = chars[i];
			if (a > 160) {
				try {
					sm.append(PinYin.getPingyin(String.valueOf(a)).toUpperCase()
							.charAt(0));
				} catch (RuntimeException e) {
				
				}
			} else if (i == 0) {
				return "";
			}
		}
		return sm.toString();
	}

	public static void main(String[] args) {
		System.out.println(PinYin.getPingyin("老师爱大米"));
		System.out.println(PinYin.getPingyinSM("老师爱大米"));
	}

}
