package com.arqui.integrador.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "mcsv-travel", url = "localhost:8080/travels")
public interface ITravelFeignClient {

}
