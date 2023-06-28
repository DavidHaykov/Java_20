package telran.library.cli.technician;

import telran.library.cli.LibraryItem;
import telran.library.model.ILibrary;
import telran.library.view.InputOutput;

import java.io.IOException;
import java.time.LocalDate;

public class GetPickedRecordsByDatesItem extends LibraryItem {
    public GetPickedRecordsByDatesItem(InputOutput inOut, ILibrary library) {
        super(inOut, library);
    }

    @Override
    public String displayedName() {
        return "Get picked records by dates";
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
        inOut.outputLine(library.getPickRecordsAtDates(dateFrom, dateTo));
    }
}
