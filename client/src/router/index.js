import Vue from 'vue'
import Router from 'vue-router';
import MedicineLabel from '../components/MedicineLabel';
import Prescription from '../components/Prescription';
import RecordBill from '../components/RecordBill';
import Drug from '../components/Drug';
import DrugDispense from '../components/DrugDispense';
Vue.use(Router);


export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
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
    ]
    
});