package test;

public class TopicRank {
	/**
	 * 计算帖子权重
	 * @param stars		收入值 
	 * @param comments	当前视频在线人数  
	 * @param sink		下沉数
	 * @param create	开播时间
	 * @return			返回得分
	 */
	public static double getScore(int stars, int comments, int sink, long create){
		int score = Math.max(stars - 1, 0) + comments - sink;
		// 投票方向
		int sign = (score == 0) ? 0 : (score > 0 ? 1 : -1);
		// 帖子争议度
		double order = Math.log10(Math.max(Math.abs(score), 1));
		// 1459440000是项目创建时间
		double seconds = create - 1459440000;
		return Double.parseDouble(String.format("%.2f",order + sign * seconds / 45000));
	}

	public static void main(String[] args) {
		java.text.DecimalFormat   df=new   java.text.DecimalFormat("#.########");
		//收入值  当前视频在线人数 开播时间
		System.out.println(df.format(getScore(10, 8, 0, System.currentTimeMillis()  )));
		System.out.println(df.format(getScore(10, 7, 0, System.currentTimeMillis()  )));
		
		System.out.println(df.format(getScore(10, 6, 0, System.currentTimeMillis()+10000  )));
		System.out.println(df.format(getScore(10, 6, 0, System.currentTimeMillis()  )));
		System.out.println(df.format(getScore(100, 6, 0, System.currentTimeMillis() -10000000 )));
	}
}
