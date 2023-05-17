package com.example.springhw14.Controller;

import com.example.springhw14.APIResponse.APIResponse;
import com.example.springhw14.Model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
public class EmoloyeeController {


    ArrayList<Employee> employees =new ArrayList<>();

    @GetMapping("/get")
    public ArrayList getEmployee(){
        return employees;
    }

    @PostMapping("/add")
//response entity class help so we can return message throu validation and حالة الطلب , 404..
//take data from body and check validation, using class erorrs methods that return T/F if there is an errors or not
//by if condition
    public ResponseEntity addEmployee(@Valid @RequestBody Employee employee, Errors errors){

        //if psttren wrong
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new APIResponse(message));
        }

        //if pattren ok
        employees.add(employee); //if pattren is ok now add users
        return ResponseEntity.status(200).body("employee has been added!");
    }

    //-------------------------------------------------

    //update
    @PutMapping("/update/{index}")
    public ResponseEntity updateEmployee(@PathVariable int index, @Valid @RequestBody Employee employee, Errors errors)
    {
        //if psttren wrong
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new APIResponse(message));
        }

        //if pattren ok
        employees.set(index,employee); //update user
        return ResponseEntity.status(200).body("Employee has been updated!");

    }



    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteUser (@PathVariable int index){
        employees.remove(index);
        return ResponseEntity.status(200).body(new APIResponse("employee has been removed!"));
    }

    //--------------------------------





//    Employees apply for an annual leave and turn their onLeave status to true and reduce annualLeave by 1
//    (Check if employee is on leave return bad request, if employee applies for leave and has 0 days return bad request)

    @PostMapping("/annualleave/{index}/{LeaveDays}")

    public ResponseEntity RequestAnnualLeave(@PathVariable int index , @PathVariable int LeaveDays){
//        if(employees.get(index).getOnLeave.equals("false"))

        if(employees.get(index).isOnLeave()){
            if(employees.get(index).getAnnualLeave()>0){
                int afterRequestAL =employees.get(index).getAnnualLeave()-LeaveDays;
                employees.get(index).setAnnualLeave(afterRequestAL);
                employees.get(index).setOnLeave(true);
            return ResponseEntity.status(200).body(" your Annual Leave has been accepted!");
            }
            else if(employees.get(index).getAnnualLeave()==0){
//                System.out.println("u have no Leave days enough");
            return ResponseEntity.status(400).body(" your Annual Leave has been declined u have no leave days left!");
            }

            else if(employees.get(index).isOnLeave()==true){
//                System.out.println("u r already in an annual leave");
            return ResponseEntity.status(400).body(" your Annual Leave has been declined u r in onr already!");

            }
        }
        return ResponseEntity.status(200).body(new APIResponse(""));

    }

}
