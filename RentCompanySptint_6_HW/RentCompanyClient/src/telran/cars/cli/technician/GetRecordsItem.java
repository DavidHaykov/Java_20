package telran.cars.cli.technician;

import telran.cars.cli.RentCompanyItem;
import telran.cars.model.IRentCompany;
import view.InputOutput;

import java.io.IOException;
import java.time.LocalDate;

public class GetRecordsItem extends RentCompanyItem {
    public GetRecordsItem(InputOutput inOut, IRentCompany company) {
        super(inOut, company);
    }

    @Override
    public String displayedName() {
        return "Get records";
    }

    @Override
    public void perform() throws IOException {
        LocalDate dateFrom = inOut.inputDate("Enter date FROM in format dd-mm-yyyy", "dd-MM-yyyy");
        if(dateFrom == null){
            return;
        }
        LocalDate dateTo = inOut.inputDate("Enter date TO in format dd-mm-yyyy", "dd-MM-yyyy");
        if(dateTo == null){
            return;
        }
        if(dateTo.isBefore(dateFrom)){
            System.out.println("Date To is BEFORE date FROM");
            return;
        }
        inOut.outputLine(company.getRentRecordsAtDate(dateFrom, dateTo));
    }
}
