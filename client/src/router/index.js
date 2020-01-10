import Vue from 'vue'
import Router from 'vue-router';
import Appointments from '../components/Appointments';
import Register from '../components/Register';
import DoctorProfile from '../components/DoctorProfile';
import MedicineLabel from '../components/MedicineLabel';
import PatientInformation from '../components/PatientInformation';
import Examination from '../components/Examination';
import Login from '../components/Login';
import personnelSelect from '../components/personnelSelect';
import docSelect from '../components/docSelect';
import RecordBill from '../components/RecordBill';
import Drug from '../components/Drug';
import DrugDispense from '../components/DrugDispense';
Vue.use(Router);


export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
            path: '/',
            component: Login
        },{
            path: '/register',
            component: Register
        },{
            path: '/doctorprofile',
            component: DoctorProfile
        },
        {
            path: '/drugdispense',
            component: DrugDispense
        },
        {
            path: '/appointments',
            component: Appointments
        },
        {
            path: '/medicineLabel',
            component: MedicineLabel
        },
        {
            path: '/patient_information',
            component: PatientInformation
        },
        {
            path: '/examination',
            component: Examination
        },
        {
            path: '/personnelSelect',
            component: personnelSelect
        },
        {
            path: '/docselect',
            component: docSelect
        },
        {
            path: '/RecordBill',
            component: RecordBill
        },
        {
            path: '/drug',
            component: Drug
        },
    ]
    
});