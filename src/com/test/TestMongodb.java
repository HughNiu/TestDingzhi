package com.test;

public class TestMongodb {

	public static void main(String[] args) {
		for(int i=8;i<=163;i++){
			String dString="db.anchor.save({\"anchorId\" : NumberLong("+i+"), \"updateTime\" : NumberLong(\""+System.currentTimeMillis()+"\"), \"status\" : 0, \"openLiveTime\" : NumberLong(\"1495622783474\"), \"sevenMoney\" : 0});";

			System.out.println(dString);
		}

	}

}
