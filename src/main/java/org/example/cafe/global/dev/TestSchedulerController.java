package org.example.cafe.global.dev;

import lombok.RequiredArgsConstructor;
import org.example.cafe.scheduler.OrderSchedulerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/scheduler")
@RequiredArgsConstructor
public class TestSchedulerController {

    private final OrderSchedulerService orderSchedulerService;

    // 버튼처럼 수동 호출용 API
    @PostMapping("/run-shipping")
    public String runShippingSchedulerNow() {
        orderSchedulerService.updateOrdersToShipping();
        return "임시 실행 완료!";
    }
}