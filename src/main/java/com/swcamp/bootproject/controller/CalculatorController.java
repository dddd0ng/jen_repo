package com.swcamp.bootproject.controller;

import com.swcamp.bootproject.dto.CalculatorDTO;
import com.swcamp.bootproject.entity.CalculationHistory;
import com.swcamp.bootproject.service.CalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class CalculatorController {

    CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "I'm Alive! Good Weather! Good Monday!!!";
    }

//    @GetMapping("/plus")
//    public ResponseEntity<CalculatorDTO> plusTwoNumbers(CalculatorDTO calculatorDTO) {
//        log.info("calculatorDTO = {}",  calculatorDTO);
//        int result = calculatorService.plusTwoNumbers(calculatorDTO);
//        calculatorDTO.setSum(result);
//
//        return ResponseEntity.ok().body(calculatorDTO);
//    }

    @PostMapping("/plus")
    public ResponseEntity<CalculatorDTO> plusTwoNumbers(@RequestBody CalculatorDTO calculatorDTO) {
        log.info("calculatorDTO = {}",  calculatorDTO);
        int result = calculatorService.plusTwoNumbers(calculatorDTO);
        calculatorDTO.setSum(result);

        return ResponseEntity.ok().body(calculatorDTO);
    }

    @GetMapping("/history")
    public ResponseEntity<List<CalculationHistory>> getHistory() {
        log.info("계산 이력 조회 요청");
        List<CalculationHistory> historyList = calculatorService.getAllHistory();
        return ResponseEntity.ok().body(historyList);

    }
}
