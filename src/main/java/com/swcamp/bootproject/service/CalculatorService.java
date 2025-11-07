package com.swcamp.bootproject.service;

import com.swcamp.bootproject.dto.CalculatorDTO;
import com.swcamp.bootproject.entity.CalculationHistory;
import com.swcamp.bootproject.repository.CalculationHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class CalculatorService {
    private final CalculationHistoryRepository calculationHistoryRepository;

    @Transactional
    public int plusTwoNumbers(CalculatorDTO calculatorDTO) {

//        return calculatorDTO.getNum1()+ calculatorDTO.getNum2();
        int result = calculatorDTO.getNum1() + calculatorDTO.getNum2();

        CalculationHistory history =
                new CalculationHistory(calculatorDTO.getNum1(), calculatorDTO.getNum2(),result);

        calculationHistoryRepository.save(history);
        log.info("계산 이력 저장 완료: {} ",history);

        return result;
    }

    @Autowired
    public CalculatorService(CalculationHistoryRepository calculationHistoryRepository) {
        this.calculationHistoryRepository = calculationHistoryRepository;
    }

    public List<CalculationHistory> getAllHistory() {
        return calculationHistoryRepository.findAllByOrderByCreatedAtDesc();
    }
}
