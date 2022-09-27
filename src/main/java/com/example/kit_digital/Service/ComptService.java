package com.example.kit_digital.Service;

import com.example.kit_digital.Entity.Compt;
import com.example.kit_digital.Entity.Customer;
import com.example.kit_digital.Entity.Role;
import com.example.kit_digital.Enum.Statu;
import com.example.kit_digital.Repo.ComptRepo;
import com.example.kit_digital.Repo.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Status;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ComptService {
private ComptRepo comptRepo;
private CustomerRepo customerRepo;

public ComptService(ComptRepo comptRepo,CustomerRepo customerRepo){
    this.comptRepo=comptRepo;
    this.customerRepo=customerRepo;
}
public List<Compt> getCompts()
        {
            long num =Double.valueOf(4).longValue();
            Customer customer = customerRepo.findById(num).orElseThrow(()->new IllegalStateException(
                    "the account with id "+num+"does not exist"
            ));

//            List<Compt> acc= customer.getCompt();
//
//           List<Compt> lastcompt= customer.getCompt().stream().filter(
//                    idf->idf.getId()==customer.getCompt().size()
//            ).collect(Collectors.toList());

//           String lastcompt2= customer.getCompt().get(customer.getCompt().size()).getStatu().name();
//           Boolean lastcompt3= customer.getCompt().get(customer.getCompt().size()).getStatu().name()=="ACCEPTED";
//           List<Compt> lastcompt4= customer.getCompt().size();
//            System.out.println("hello 2");
            List<Compt> all = comptRepo.findAll();
            return all;
         }

    public Compt getComptById(Long id)
    {
        Compt compt = comptRepo.findById(id).orElseThrow(()->new IllegalStateException(
                "the account with id "+id+"does not exist"
        ));;
        return compt;
    }
public void saveCompt(Compt compt,Long customorId){
    Customer customer = customerRepo.findById(customorId)
            .orElseThrow(()->new IllegalStateException(
                    "the account with id "+customorId+"does not exist"
            ));


 if((customer.getCompt().size()!=0))
{
    if ((customer.getCompt().get(customer.getCompt().size()).getStatu().name()=="REFUSED")) {
        comptRepo.save(compt);
    }
    else {
        log.info("your account already working");
    }
}
 else {
     compt.setCustomer(customer);
     comptRepo.save(compt);
 }
}

@Transactional
public void UpdateCompt(Long id, Statu statu){

    Compt compt = comptRepo.findById(id)
            .orElseThrow(()->new IllegalStateException(
                    "the account with id "+id+"does not exist"
    ));

    if (statu != null){
        compt.setStatu(statu);
    }
}

}
