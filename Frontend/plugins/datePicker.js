import Vue from 'vue'
import DatePickerr from 'vue-persian-datetime-picker'
Vue.component('date-picker', DatePickerr);
Vue.use(DatePickerr, {
  name: 'custom-date-picker',
  props: {

    format: 'jYYYY/jMM/jDD',
    editable: true,
    inputClass: 'form-control my-custom-class-name',
    placeholder: '13xx/xx/xx',
    altFormat: 'YYYY-MM-DD',
    color: '#7E1975',
    autoSubmit: true,
    //...
    //... And whatever you want to set as default
    //...
  }
});
