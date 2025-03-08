import { createRouter, createWebHistory } from 'vue-router';
import ProductList from '@/views/ProductList.vue'; // 確保路徑正確
import AddProduct from '@/views/AddProduct.vue';  
import EditProduct from '@/views/EditProduct.vue';  

const routes = [
    {
        path: '/products',
        name: 'products',
        component: ProductList,
    },
    {
        path: '/add-product',
        name: 'AddProduct',
        component: AddProduct,
    },
    {
        path: '/edit-product/:LikelistNo',
        name: 'EditProduct',
        component: EditProduct,  
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
