import Vue from 'vue'
import Router from 'vue-router';
import MedicineLabel from '../components/MedicineLabel';
import Prescription from '../components/Prescription';
import RecordBill from '../components/RecordBill';
import Drug from '../components/Drug';
import DrugDispense from '../components/DrugDispense';
import Pharmacist from '../components/Pharmacist';
import ViewDrug from '../components/ViewDrug';
import ViewPharmacist from '../components/ViewPharmacist';
import Login from '../components/Login';
import Home from '../components/Home';
Vue.use(Router);


export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            component: Login
        },
        {
            path: '/login',
            component: Login
        },
        {
            path: '/home',
            component: Home
        },
        {
            path: '/drugdispense',
            component: DrugDispense
        },
        {
            path: '/medicineLabel',
            component: MedicineLabel
        },
        {
            path: '/prescription',
            component: Prescription
        },
        {
            path: '/RecordBill',
            component: RecordBill
        },
        {
            path: '/drug',
            component: Drug
        },
        {
            path: '/pharmacist',
            component: Pharmacist
        },
        {
            path: '/viewdrug',
            component: ViewDrug
        },
        {
            path: '/viewpharmacist',
            component: ViewPharmacist
        },

    ]
    
});