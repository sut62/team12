package com.cpe.backend;

//Pharmacist 
import com.cpe.backend.Entity.TitlePharmacist;
import com.cpe.backend.Entity.Province;
import com.cpe.backend.Entity.Sex;
import com.cpe.backend.repository.TitlePharmacistRepository;
import com.cpe.backend.repository.ProvinceRepository;
import com.cpe.backend.repository.SexRepository;

//DrugDispense
import com.cpe.backend.Entity.DrugDispenseChannel;
import com.cpe.backend.repository.DrugDispenseChannelRepository;

//Drug  
import com.cpe.backend.Entity.DrugCategory;
import com.cpe.backend.repository.DrugCategoryRepository;


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

//MedicineLabel
import com.cpe.backend.Entity.MedicineQuantity;
import com.cpe.backend.Entity.MedicineFrequency;
import com.cpe.backend.Entity.MedicineDuration;
import com.cpe.backend.repository.MedicineQuantityRepository;
import com.cpe.backend.repository.MedicineFrequencyRepository;
import com.cpe.backend.repository.MedicineDurationRepository;

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
	ApplicationRunner init(final TitlePharmacistRepository titlepharmacistRepository,
	final SexRepository sexRepository, final ProvinceRepository provinceRepository,final DoctorRepository doctorRepository, 
			final TitleNameRepository titleNameRepository,
			final Unit_of_medicineRepository unit_of_medicineRepository,
			final PaymentChannelRepository paymentChannelRepository, 
			final CashierRepository cashierRepository,
			final MedicalRightRepository medicalRightRepository,
			final DrugCategoryRepository drugcategoryRepository,
			final DrugDispenseChannelRepository drugDispenseChannelRepository,
			final MedicineQuantityRepository medicineQuantityRepository, 
			final MedicineFrequencyRepository medicineFrequencyRepository,
			final MedicineDurationRepository medicineDurationRepository
			) {
		return args -> {

			Stream.of("ช่อง 1","ช่อง 2","ช่อง 3").forEach(drugdispensechannelX -> {
				final DrugDispenseChannel drugdispensechannel = new DrugDispenseChannel();
				drugdispensechannel.setDrugdispensechannel(drugdispensechannelX);
				drugDispenseChannelRepository.save(drugdispensechannel);
			});


			Stream.of("ภก.","ภกญ.").forEach(titlePharm -> {
				TitlePharmacist titlepharmacist = new TitlePharmacist();
				titlepharmacist.setTitlepharmacist(titlePharm);
				titlepharmacistRepository.save(titlepharmacist);
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

			Stream.of("ชาย","หญิง").forEach(titleSex -> {
				Sex sex = new Sex();
				sex.setSex(titleSex);
				sexRepository.save(sex);
			});

			Stream.of("ยาสามัญประจำบ้าน", "ยาควบคุมพิเศษ", "ยาอันตราย").forEach(category -> {
				DrugCategory drugCategory = new DrugCategory();
				drugCategory.setCategory(category);
				drugcategoryRepository.save(drugCategory);
			});

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
			
		//toonjung
			Stream.of("ช้อนชา", "ช้อนโต๊ะ", "เม็ด","แคปซูล").forEach(medicineQuantitys -> {
				MedicineQuantity medicineQuantity = new MedicineQuantity();
				medicineQuantity.setMedicineQuantity(medicineQuantitys);
				medicineQuantityRepository.save(medicineQuantity);
			});
		
			Stream.of("1 ครั้ง", "2 ครั้ง", "3 ครั้ง","4 ครั้ง").forEach(medicineFrequencys -> {
				 MedicineFrequency medicineFrequency = new MedicineFrequency();
				medicineFrequency.setMedicineFrequency(medicineFrequencys);
				medicineFrequencyRepository.save(medicineFrequency);
			});

			Stream.of("เช้า กลางวัน เย็น", "เช้า เย็น", "เช้า กลาวัน ก่อนนอน","ก่อนนอน").forEach(medicineDurations -> {
				 MedicineDuration medicineDuration = new MedicineDuration();
				medicineDuration.setMedicineDuration(medicineDurations);
				medicineDurationRepository.save(medicineDuration);
			});

			
			medicineQuantityRepository.findAll().forEach(System.out::println);
			medicineFrequencyRepository.findAll().forEach(System.out::println);
			medicineDurationRepository.findAll().forEach(System.out::println);
			titlepharmacistRepository.findAll().forEach(System.out::println);
			provinceRepository.findAll().forEach(System.out::println);
			sexRepository.findAll().forEach(System.out::println);
			drugcategoryRepository.findAll().forEach(System.out::println);
			doctorRepository.findAll().forEach(System.out::println);
			titleNameRepository.findAll().forEach(System.out::println);
			unit_of_medicineRepository.findAll().forEach(System.out::println);
			medicalRightRepository.findAll().forEach(System.out::println);
			paymentChannelRepository.findAll().forEach(System.out::println);
			cashierRepository.findAll().forEach(System.out::println);
			drugDispenseChannelRepository.findAll().forEach(System.out::println);
		};
	}

}
