<template>
    <div class="p-6">
        <h1 class="text-2xl font-bold mb-4 text-center">修改產品</h1>

        <form @submit.prevent="submitForm">
            <div class="mb-4" hidden>
                <label for="Likelist" class="block text-lg font-medium">產品編號</label>
                <input v-model="product.Likelist" type="number" id="Likelist"
                    class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-md bg-gray-100 text-gray-500 cursor-not-allowed"
                    />
            </div>

            <div class="mb-4">
                <label for="ProductName" class="block text-lg font-medium">產品名稱</label>
                <input v-model="product.ProductName" type="text" id="ProductName"
                    class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-md bg-gray-100 text-gray-500 cursor-not-allowed"
                    />
            </div>

            <div class="mb-4">
                <label for="productPrice" class="block text-lg font-medium">產品價格</label>
                <input v-model="product.Price" type="number" id="productPrice"
                    class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-md bg-gray-100 text-gray-500 cursor-not-allowed"
                    />
            </div>

            <div class="mb-4">
                <label for="FeeRate" class="block text-lg font-medium">手續費率</label>
                <input v-model="product.FeeRate" type="text" id="FeeRate"
                    class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-md bg-gray-100 text-gray-500 cursor-not-allowed"
                    min="0" max="1" step="0.01"/>
            </div>

            <div class="mb-4">
                <label for="Account" class="block text-lg font-medium">預計要扣款的帳號</label>
                <select v-model="product.Account" id="Account"
                    class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-md">
                    <option value="product.Account" disabled>選擇帳號</option>
                    <option v-for="account in accounts" :key="account" :value="account">
                        {{ account }}
                    </option>
                </select>
            </div>

            <div class="mb-4">
                <label for="OrderName" class="block text-lg font-medium">購買數量</label>
                <input v-model="product.OrderName" type="number" id="OrderName"
                    class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-md" required min="1" step="1" />
            </div>

            <div class="flex justify-center mt-4">
                <button type="submit" @click="submitLikeList"
                    class="px-6 py-2 bg-black text-white rounded-md hover:bg-gray-800 transition duration-200">
                    修改產品
                </button>

                <button @click="goBack"
                    class="px-6 py-2 bg-black text-white rounded-md hover:bg-gray-600 transition duration-200">
                    返回上一頁
                </button>
            </div>
        </form>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router'; // 引入 useRoute
import axios from 'axios';

// 使用 useRoute 來取得當前路由資料
const route = useRoute();

const product = ref({
    Likelist: "",
    ProductName: "",
    Price: "",
    FeeRate: "",
    account: "",
    quantity: 1,
});

// 儲存帳號資料
const accounts = ref([]);

const fetchProducts = async () => {
    try {
        const response = await axios.get("http://localhost:8080/api/products/getProductInformation", {
            params: {
                LikelistNo: route.params.LikelistNo,
            },
        });
        if (response.data) {
            product.value = {
                Likelist: response.data.LikelistNo,
                ProductName: response.data.ProductName,
                Price: response.data.Price,
                FeeRate: response.data.FeeRate,
                Account: response.data.Account,
                OrderName: response.data.OrderName, // 預設購買數量
            };
        }
    } catch (error) {
        console.error("獲取商品資訊失敗", error);
    }
};

// 取得帳號資料的函數
const fetchAccounts = async () => {
    try {
        const response = await axios.get('http://localhost:8080/api/users/getAccounts');
        accounts.value = response.data; // 儲存從 API 獲得的帳號資料
    } catch (error) {
        console.error("獲取帳號資料失敗", error);
    }
};

const goBack = () => {
    // 返回上一頁
    window.history.back();
};

function submitLikeList() {
    const requestBody = {
        Likelist: product.value.Likelist,
        ProductName: product.value.ProductName,
        Price: product.value.Price,
        FeeRate: product.value.FeeRate,
        Account: product.value.Account,
        OrderName: product.value.OrderName,
    };
    axios.post('http://localhost:8080/api/LikeList/editLikeList', requestBody)
        .then(response => {
            console.log(response); // 打印 response 或做其他處理
            alert('成功修改產品');
        })
        .catch(error => {
            alert('修改失敗');
            console.error(error);
        });
}

// 生命週期鉤子，組件掛載時呼叫
onMounted(() => {
    fetchAccounts();
    fetchProducts();
});

</script>

<style scoped>
/* 可以加一些額外的樣式，根據需要調整 */
</style>