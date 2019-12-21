package com.cpe.backend;

//Drug  package com.cpe.backend.Entity
import com.cpe.backend.Entity.DrugCategory;
import com.cpe.backend.Entity.DrugType;



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
	ApplicationRunner init(
							DurationRepository durationRepository, 
							TitleNameRepository titlenameRepository, 
							GenderRepository genderRepository ,
							BloodGroupRepository bloodgroupRepository, 
							ProvinceRepository provinceRepository,
							TitleR titleRepository, 
							ExpertiseR expertiseRepository,
							SexR sexRepository,
							DayRepository dayRepository,
							PeriodTimeRepository periodTimeRepository ,
							RoomRepository roomRepository,
							Patient_typeR patient_typeR,
							StatusR statusR,
							DiseaseRepository diseaseRepository, 
							Treatment_TypeRepository treatmentRepository,
							Severity_LevelRepository levelRepository,
							PatientRepository patientRepository
							) {
		return args -> {
				

			Stream.of("08:00-10:00 น.", "10:00-12:00 น.", "13:00-15:00 น.", "15:00-17:00 น.").forEach(time -> {
				Duration duration = new Duration(); // สร้าง Object Time
				duration.setTime(time); // set ชื่อ (name) ให้ Object ชื่อ Time
				durationRepository.save(duration); // บันทึก Objcet ชื่อ Time
			});

			Stream.of("นาย", "นาง", "นางสาว", "เด็กชาย", "เด็กหญิง").forEach(name-> {
				TitleName titleName = new TitleName();
			    titleName.setName(name);
				titlenameRepository.save(titleName);
			});

			Stream.of("ชาย", "หญิง").forEach(name-> {
				Gender gender = new Gender();
				gender.setName(name);
				genderRepository.save(gender);
            });
            
            Stream.of("A", "B", "O","AB").forEach(type -> {
				BloodGroup bloodGroup = new BloodGroup();
				bloodGroup.setType(type);
				bloodgroupRepository.save(bloodGroup);
			});

			Stream.of("กรุงเทพมหานคร", "กระบี่", "กาญจนบุรี", "กาฬสินธุ์", "กำแพงเพชร", "ขอนแก่น", "จันทบุรี", "ฉะเชิงเทรา", "ชลบุรี", "ชัยนาท"
					, "ชัยภูมิ", "ชุมพร", "เชียงราย", "เชียงใหม่", "ตรัง", "ตราด", "ตาก", "นครนายก", "นครปฐม", "นครพนม", "นครราชสีมา", "นครศรีธรรมราช"
					, "นครสวรรค์", "นนทบุรี", "นราธิวาส", "น่าน", "บึงกาฬ", "บุรีรัมย์", "ปทุมธานี", "ประจวบคีรีขันธ์", "ปราจีนบุรี", "ปัตตานี", "พระนครศรีอยุธยา"
					, "พังงา", "พัทลุง", "พิจิตร", "พิษณุโลก", "เพชรบุรี", "เพชรบูรณ์", "แพร่", "พะเยา", "ภูเก็ต", "มหาสารคาม", "มุกดาหาร", "แม่ฮ่องสอน"
					, "ยะลา", "ยโสธร", "ร้อยเอ็ด", "ระนอง", "ระยอง", "ราชบุรี", "ลพบุรี", "ลำปาง", "ลำพูน", "เลย", "ศรีสะเกษ", "สกลนคร", "สงขลา", "สตูล"
					, "สมุทรปราการ", "สมุทรสงคราม", "สมุทรสาคร", "สระแก้ว", "สระบุรี", "สิงห์บุรี", "สุโขทัย", "สุพรรณบุรี", "สุราษฎร์ธานี", "สุรินทร์", "หนองคาย"
					, "หนองบัวลำภู", "อ่างทอง", "อุดรธานี", "อุทัยธานี", "อุตรดิตถ์", "อุบลราชธานี", "อำนาจเจริญ").forEach(provinces -> {
				Province province = new Province();
				province.setProvince(provinces);
				provinceRepository.save(province);
			});

			Stream.of("นายแพทย์","แพทย์หญิง","เทคนิคการแพทย์","เทคนิคการแพทย์หญิง","ทันตแพทย์","ทันตแพทย์หญิง").forEach(TitleNames-> {
				Title title = new Title();
			    title.setTitle(TitleNames);
				titleRepository.save(title);
			});

			Stream.of("หู คอ จมูก","กระดูก ข้อ","จักษุวิทยา","อายุรศาสตร์","ประสาทวิทยา","รังสีวิทยา","พยาธิวิทยา","ศัลยกรรม","กุมารเวชศาสตร์","เวชศาสตร์ฟื้นฟู","จิตเวชศาสตร์").forEach(skills-> {
				Expertise expertise = new Expertise();
				expertise.setExpertise(skills);
				expertiseRepository.save(expertise);
            });
            
            Stream.of("ชาย","หญิง").forEach(gender -> {
				Sex sex = new Sex();
				sex.setSex(gender);
				sexRepository.save(sex);
			});

			Stream.of("Monday", "Thuesday", "Wendenday", "Thursday", "Friday").forEach(days-> {
				Day day = new Day();
			    day.setDay(days);
				dayRepository.save(day);
			});

			Stream.of("08.00-09.00", 
					"09.00-10.00",
					"10.00-11.00",
					"11.00-12.00",
					"12.00-13.00",
					"13.00-14.00", 
					"14.00-15.00",
					"15.00-16.00").forEach(periodTimes-> {
				
			PeriodTime periodTime = new PeriodTime();
			periodTime.setPeriodTime(periodTimes);
			periodTimeRepository.save(periodTime);
            });
            
            Stream.of("ห้องตรวจ1", "ห้องตรวจ2", "ห้องตรวจ3","ห้องตรวจ4","ห้องตรวจ5").forEach(rooms -> {
				Room room = new Room();
				room.setRoom(rooms);
				roomRepository.save(room);
			});

			Stream.of("ผู้ป่วยนอก", "มาตามนัด").forEach(Patient_types-> {
				Patient_type patient_type = new Patient_type();
			    patient_type.setPatient_type(Patient_types);
				patient_typeR.save(patient_type);
			});

			Stream.of("เดินมา", "รถนั่ง", "รถนอน").forEach(Statuss-> {
				Status status = new Status();
				status.setStatus(Statuss);
				statusR.save(status);
			});

			Stream.of("โรคกระเพาะ", "ไข้หวัดใหญ่", "ไข้หวัด", "ไมเกรน", "ไส้ติ่ง", "โรคเครียด").forEach(diseaseX -> {
				Disease disease_name = new Disease();
				disease_name.setDisease_name(diseaseX);
				diseaseRepository.save(disease_name);
			});

			Stream.of("กลับบ้าน", "นัดติดตามอาการ", "รอดูอาการ", "Admit").forEach(typeX -> {
				Treatment_Type type_name = new Treatment_Type();
				type_name.setType_name(typeX);
				treatmentRepository.save(type_name);
			});

			Stream.of("น้อย", "ปานกลาง", "มาก", "มากที่สุด").forEach(levelX -> {
				Severity_Level level = new Severity_Level();
				level.setLevel(levelX);
				levelRepository.save(level);
			});


			//patientRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Patient บน Terminal
			//employeeRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Employee บน Terminal
			//durationRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Time บน Terminal
			//roomRepository.findAll().forEach(System.out::println); // แสดง ข้อมูลทั้งหมดใน Entity Room บน Terminal
		};
	}

}
