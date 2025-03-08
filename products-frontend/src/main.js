import { createApp } from 'vue';
import App from './App.vue';
import router from './router'; // 引入 router
import 'bootstrap/dist/css/bootstrap.min.css';

createApp(App)
    .use(router)  // 註冊 router
    .mount('#app');
