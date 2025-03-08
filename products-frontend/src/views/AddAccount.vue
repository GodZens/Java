<template>
    <div class="p-6">
        <h1 class="text-2xl font-bold mb-4 text-center">新增帳號</h1>
        <form @submit.prevent="submitForm">
            <div class="mb-4">
                <label for="userId" class="block text-lg font-medium">使用者ID</label>
                <input v-model="user.userId" type="text" id="userId"
                    class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-md bg-gray-100 text-gray-500 cursor-not-allowed"
                    />
            </div>

            <div class="mb-4">
                <label for="userName" class="block text-lg font-medium">使用者名稱</label>
                <input v-model="user.userName" type="text" id="userName"
                    class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-md bg-gray-100 text-gray-500 cursor-not-allowed"
                    />
            </div>

            <div class="mb-4">
                <label for="email" class="block text-lg font-medium">使用者電子郵件</label>
                <input v-model="user.email" type="text" id="email"
                    class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-md bg-gray-100 text-gray-500 cursor-not-allowed"
                    />
            </div>

            <div class="mb-4">
                <label for="account" class="block text-lg font-medium">扣款帳號</label>
                <input v-model="user.account" type="text" id="account"
                    class="w-full px-4 py-2 mt-2 border border-gray-300 rounded-md bg-gray-100 text-gray-500 cursor-not-allowed"
                    />
            </div>

            <div class="flex justify-center mt-4">
                <button type="submit" @click="submitLikeList"
                    class="px-6 py-2 bg-black text-white rounded-md hover:bg-gray-800 transition duration-200">
                    新增帳號
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

const user = ref({
    userId: "",
    userName: "",
    email: "",
    account: ""
});

const goBack = () => {
    // 返回上一頁
    window.history.back();
};

function submitLikeList() {
    const requestBody = {
        userId: user.value.userId,
        userName: user.value.userName,
        email: user.value.email,
        account: user.value.account,
    };
    axios.post('http://localhost:8080/api/users/addUser', requestBody)
        .then(response => {
            console.log(response); // 打印 response 或做其他處理
            alert('成功提交產品');
        })
        .catch(error => {
            alert('提交失敗');
            console.error(error);
        });
}
</script>
