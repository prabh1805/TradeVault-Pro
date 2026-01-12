package com.tradevault.pro;

import org.springframework.boot.SpringApplication;

public class TestTradeVaultProApplication {

	public static void main(String[] args) {
		SpringApplication.from(TradeVaultProApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
