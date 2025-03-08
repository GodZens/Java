<template>
    <div class="p-6">
        <h1 class="text-2xl font-bold mb-4 text-center">新增產品</h1>

        <form @submit.prevent="submitForm">
            <div class="mb-4">
                <label for="productName" class="block text-lg font-medium">產品名稱</label>
                <input v-model="product.productName" type="text" id="productName"
                    class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-md bg-gray-100 text-gray-500 cursor-not-allowed"
                    />
            </div>

            <div class="mb-4">
                <label for="productPrice" class="block text-lg font-medium">產品價格</label>
                <input v-model="product.price" type="number" id="productPrice"
                    class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-md bg-gray-100 text-gray-500 cursor-not-allowed"
                    />
            </div>

            <div class="mb-4">
                <label for="feeRate" class="block text-lg font-medium">手續費率</label>
                <input v-model="product.feeRate" type="number" id="feeRate"
                    class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-md bg-gray-100 text-gray-500 cursor-not-allowed"
                    min="0" max="1" step="0.01"/>
            </div>

            <div class="mb-4">
                <label for="account" class="block text-lg font-medium">預計要扣款的帳號</label>
                <select v-model="product.account" id="account"
                    class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-md">
                    <option value="" disabled>選擇帳號</option>
                    <option v-for="account in accounts" :key="account" :value="account">
                        {{ account }}
                    </option>
                </select>
            </div>

            <div class="mb-4">
                <label for="ordername" class="block text-lg font-medium">購買數量</label>
                <input v-model="product.ordername" type="number" id="ordername"
                    class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-md" required min="1" step="1" />
            </div>

            <div class="flex justify-center mt-4">
                <button type="submit" @click="submitLikeList"
                    class="px-6 py-2 bg-black text-white rounded-md hover:bg-gray-800 transition duration-200">
                    新增產品
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
import axios from 'axios';

const product = ref({
    productName: "",
    price: "",
    feeRate: 0,
    account: "",
    ordername: 1,
});

// 儲存帳號資料
const accounts = ref([]);

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
        productName: product.value.productName,
        price: product.value.price,
        feerate: product.value.feeRate,
        account: product.value.account,
        ordername: product.value.ordername,
    };
    axios.post('http://localhost:8080/api/LikeList/addLikeList', requestBody)
        .then(response => {
            console.log(response); // 打印 response 或做其他處理
            alert('成功提交產品');
        })
        .catch(error => {
            alert('提交失敗');
            console.error(error);
        });
}

// 生命週期鉤子，組件掛載時呼叫
onMounted(() => {
    fetchAccounts();
});

</script>
