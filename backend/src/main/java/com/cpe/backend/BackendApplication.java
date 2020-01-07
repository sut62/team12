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

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	ApplicationRunner init(DoctorRepository doctorRepository, TitleNameRepository titleNameRepository,
			Unit_of_medicineRepository unit_of_medicineRepository) {
		return args -> {
			Stream.of("จตุพล สนิทไทย", "ทศพล ตะสันเทียะ", "ณรงค์ศักดิ์ สุวรรณ", "สโรชา สุขสบาย", "ประวีร์ ยุทธวีระวงศ์",
					"เจนจิรา กล่อมตระกูล").forEach(doctorX -> {
						Doctor doctor_name = new Doctor();
						doctor_name.setDoctor_name(doctorX);
						doctorRepository.save(doctor_name);
					});

			Stream.of("ด.ช.", "ด.ญ.", "นาย", "นาง", "นางสาว").forEach(titleNameX -> {
				TitleName titlename = new TitleName();
				titlename.setTitlename(titleNameX);
				titleNameRepository.save(titlename);
			});

			Stream.of("เม็ด", "แคปซูล", "ขวด", "ซอง", "หลอด").forEach(unitX -> {
				Unit_of_medicine unit = new Unit_of_medicine();
				unit.setUnit(unitX);
				unit_of_medicineRepository.save(unit);
			});

			doctorRepository.findAll().forEach(System.out::println);
			titleNameRepository.findAll().forEach(System.out::println);
			unit_of_medicineRepository.findAll().forEach(System.out::println);
		};
	}

}
