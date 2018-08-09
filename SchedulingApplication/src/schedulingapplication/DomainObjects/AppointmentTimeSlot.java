package schedulingapplication.DomainObjects;

import java.time.LocalDateTime;


public class AppointmentTimeSlot {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    
    public AppointmentTimeSlot(LocalDateTime startDate, LocalDateTime endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public LocalDateTime getStartDate(){
        return startDate;
    }
    
    public void setStartDate(LocalDateTime startDate) {
        startDate = this.startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDateTime endDate) {
        endDate = this.endDate;
    }
}
