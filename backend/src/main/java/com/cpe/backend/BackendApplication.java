package com.cpe.backend;

//Drug  package com.cpe.backend.Entity
import com.cpe.backend.Entity.DrugCategory;
import com.cpe.backend.Entity.DrugType;
//Prescription
import com.cpe.backend.Entity.Doctor;
import com.cpe.backend.Entity.TitleName;
import com.cpe.backend.Entity.Unit_of_medicine;
import com.cpe.backend.repository.DoctorRepository;
import com.cpe.backend.repository.TitleNameRepository;
import com.cpe.backend.repository.Unit_of_medicineRepository;

//RecordBill
import com.cpe.backend.Entity.Cashier;
import com.cpe.backend.Entity.PaymentChannel;
import com.cpe.backend.Entity.MedicalRight;
import com.cpe.backend.repository.CashierRepository;
import com.cpe.backend.repository.MedicalRightRepository;
import com.cpe.backend.repository.PaymentChannelRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class BackendApplication {

	public static void main(final String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	ApplicationRunner init(final DoctorRepository doctorRepository, final TitleNameRepository titleNameRepository,
			final Unit_of_medicineRepository unit_of_medicineRepository,
			final PaymentChannelRepository paymentChannelRepository, final CashierRepository cashierRepository,
			final MedicalRightRepository medicalRightRepository) {
		return args -> {
			Stream.of("จตุพล สนิทไทย", "ทศพล ตะสันเทียะ", "ณรงค์ศักดิ์ สุวรรณ", "สโรชา สุขสบาย", "ประวีร์ ยุทธวีระวงศ์",
					"เจนจิรา กล่อมตระกูล").forEach(doctorX -> {
						final Doctor doctor_name = new Doctor();
						doctor_name.setDoctor_name(doctorX);
						doctorRepository.save(doctor_name);
					});

			Stream.of("ด.ช.", "ด.ญ.", "นาย", "นาง", "นางสาว").forEach(titleNameX -> {
				final TitleName titlename = new TitleName();
				titlename.setTitlename(titleNameX);
				titleNameRepository.save(titlename);
			});

			Stream.of("เม็ด", "แคปซูล", "ขวด", "ซอง", "หลอด").forEach(unitX -> {
				final Unit_of_medicine unit = new Unit_of_medicine();
				unit.setUnit(unitX);
				unit_of_medicineRepository.save(unit);
			});

			Stream.of("ประกันสังคม", "บัตร30บาท", "ข้าราชการ").forEach(medicalRightX -> {
				final MedicalRight medicalRight = new MedicalRight();
				medicalRight.setMedicalRight(medicalRightX);
				medicalRightRepository.save(medicalRight);
			});

			Stream.of("ช่องชำระที่ 1", "ช่องชำระที่ 2", "ช่องชำระที่ 3").forEach(channelX -> {
				final PaymentChannel Channel = new PaymentChannel();
				Channel.setChannel(channelX);
				paymentChannelRepository.save(Channel);
			});

			Stream.of("โดนัล ทรัมป์", "ประยุทธ์", "บูติน").forEach(cashierX -> {
				final Cashier cashier = new Cashier();
				cashier.setCashier(cashierX);
				cashierRepository.save(cashier);
			});

			doctorRepository.findAll().forEach(System.out::println);
			titleNameRepository.findAll().forEach(System.out::println);
			unit_of_medicineRepository.findAll().forEach(System.out::println);
			medicalRightRepository.findAll().forEach(System.out::println);
			paymentChannelRepository.findAll().forEach(System.out::println);
			cashierRepository.findAll().forEach(System.out::println);
		};
	}

}
