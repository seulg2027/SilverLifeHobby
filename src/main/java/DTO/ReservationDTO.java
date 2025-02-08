package DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
	private int reservationId;
	private Date reservationTime;
	private int userId;
	private int scheduleId;
	private int centerinfoId;
}