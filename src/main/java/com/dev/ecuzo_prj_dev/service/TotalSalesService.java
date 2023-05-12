package com.dev.ecuzo_prj_dev.service;

import com.dev.ecuzo_prj_dev.dto.TotalSalesDto;
import com.dev.ecuzo_prj_dev.entity.TotalSales;
import com.dev.ecuzo_prj_dev.entity.Users;
import com.dev.ecuzo_prj_dev.jpa.TotalSalesRepository;
import com.dev.ecuzo_prj_dev.jpa.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TotalSalesService {
    private TotalSalesRepository totalSalesRepository ;
    private UserRepository userRepository;
    @Autowired
    public TotalSalesService(TotalSalesRepository totalSalesRepository,UserRepository userRepository) {
        this.totalSalesRepository = totalSalesRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<TotalSalesDto> totalSalesList() {
        List<TotalSales> totalSales = totalSalesRepository.findAll(Sort.by(Sort.Direction.DESC,"updateAt"));
        List<TotalSalesDto> totalSalesDto = totalSales.stream()
                .map(m -> m.toDto())
                .collect(Collectors.toList());
        return totalSalesDto;
    }
    @Transactional
    public void totalSalesUpdate(String tableNum, TotalSalesDto dto){
        Users users=userRepository.findByTableNum(tableNum);
        int id = users.getId();
        List<TotalSales> totalSalesList= totalSalesRepository.findAllById(id);
        TotalSales totalSalesUpdate =totalSalesList.get(totalSalesList.size()-1);
        totalSalesUpdate =dto.toEntity();
        totalSalesRepository.save(totalSalesUpdate);
        System.out.println("DB 저장 완료");
    }

//    public void totalSalesDelete(String tableNum) {
//        TotalSales totalSales= totalSalesRepository.findByTableNum(tableNum);
//        totalSalesRepository.delete(totalSales);
//    }
}
