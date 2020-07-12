import Vue from 'vue'
import DatePickerr from 'vue-persian-datetime-picker'
Vue.component('date-picker', DatePickerr);
Vue.use(DatePickerr, {
  name: 'custom-date-picker',
  props: {
    format: 'jYYYY/jMM/jDD',
    editable: false,
    inputClass: 'form-control border rounded-0 text-right',
    placeholder: '13xx/xx/xx',
    altFormat: 'YYYY-MM-DD',
    color: '#00acc1',
    autoSubmit: true,
    //...
    //... And whatever you want to set as default
    //...
  }
});
// Vue.use(DatePickerr, {
//   name: 'my-date-picker',
//   props: {
//     inputFormat: 'YYYY-MM-DD HH:mm',
//     format: 'jYYYY-jMM-jDD HH:mm',
//     editable: false,
//     inputClass: 'form-control my-custom-class-name',
//     placeholder: 'لطفا تاریخ و زمان را تعیین کنید',
//     altFormat: 'YYYY-MM-DD HH:mm',
//     color: '#00acc1',
//     autoSubmit: false,
//     //...
//     //... And whatever you want to set as default
//     //...
//   }
// });
