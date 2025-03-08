<script setup>
import { ref, onMounted, computed } from "vue";
import { useRouter } from 'vue-router'
import axios from "axios";

const router = useRouter()
// 資料 & 分頁控制
const products = ref([]); // 存放 API 回傳的資料
const currentPage = ref(1); // 當前頁數
const pageSize = ref(10); // 每頁顯示 10 筆
const totalItems = ref(0); // 總資料筆數

// 計算總頁數
const totalPages = computed(() => Math.ceil(totalItems.value / pageSize.value));

// 從後端取得資料
const fetchProducts = async () => {
    try {
        const response = await axios.get("http://localhost:8080/api/products/list", {
            params: {
                page: currentPage.value,
                pageSize: pageSize.value,
            },
        });

        products.value = response.data.products; // 存入產品資料
        totalItems.value = response.data.total; // 存入總筆數
    } catch (error) {
        console.error("資料載入失敗:", error);
    }
};
//新增商品
const goToAddPage = () => {
    router.push({ name: 'AddProduct', })
}

const goToEditPage = (LikelistNo) => {
    router.push({ name: 'EditProduct', params: { LikelistNo: LikelistNo } })
}

const deleteProduct = (LikelistNo) => {
    const requestBody = {
        LikelistNo: LikelistNo,
    };
    axios.post('http://localhost:8080/api/LikeList/deleteLikeList', requestBody)
    .then(response => {
            console.log(response); // 打印 response 或做其他處理
            alert('成功刪除產品');
            window.location.reload(); // 重新載入當前頁面
        })
        .catch(error => {
            alert('刪除失敗');
            console.error(error);
        });
}

// 切換頁數
const changePage = (page) => {
    if (page >= 1 && page <= totalPages.value) {
        currentPage.value = page;
        fetchProducts(); // 重新載入資料
    }
};

window.addEventListener("pageshow", (event) => {
    if (event.persisted) {
        window.location.reload(); // 強制重新加載頁面
    }
});

// 初次載入資料
onMounted(fetchProducts);
</script>

<template>
    <div class="p-6">
        <h1 class="text-2xl font-bold mb-4 text-center">產品列表</h1>

        <button  @click="goToAddPage()"
            class="px-4 py-2 bg-white mb-4 text-black border border-gray-300 rounded-md hover:bg-gray-200 transition duration-200 text-sm">
            新增帳號
        </button>

        <button  @click="goToAddPage()"
            class="px-4 py-2 bg-white mb-4 text-black border border-gray-300 rounded-md hover:bg-gray-200 transition duration-200 text-sm">
            新增產品
        </button>

        <!-- 產品表格 -->
        <table class="w-full border-collapse border border-gray-300 mx-auto">
            <thead class="bg-gray-100">
                <tr>
                    <th class="border border-gray-300 p-2 text-center">金融商品</th>
                    <th class="border border-gray-300 p-2 text-center">預計要扣款的帳號</th>
                    <th class="border border-gray-300 p-2 text-center">預計扣款總金額</th>
                    <th class="border border-gray-300 p-2 text-center">總手續費用</th>
                    <th class="border border-gray-300 p-2 text-center">扣款帳號</th>
                    <th class="border border-gray-300 p-2 text-center">使用者聯絡電子信箱</th>
                    <th class="border border-gray-300 p-2 text-center">按鈕</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="product in products" :key="product.No">
                    <td class="border border-gray-300 p-2 text-center" hidden>{{ product.LikelistNo }}</td>
                    <td class="border border-gray-300 p-2 text-center">{{ product.ProductName }}</td>
                    <td class="border border-gray-300 p-2 text-center">{{ product.PreAccount }}</td>
                    <td class="border border-gray-300 p-2 text-center">{{ product.TotalAmount }}</td>
                    <td class="border border-gray-300 p-2 text-center">{{ product.TotalFee }}</td>
                    <td class="border border-gray-300 p-2 text-center">{{ product.Account }}</td>
                    <td class="border border-gray-300 p-2 text-center">{{ product.Email }}</td>
                    <td class="border border-gray-300 p-2 text-center flex space-x-4 justify-center">
                        <button @click="goToEditPage(product.LikelistNo)"
                            class="px-4 py-2 bg-white text-black border border-gray-300 rounded-md hover:bg-gray-200 transition duration-200 text-sm">修改</button>
                        <button @click="deleteProduct(product.LikelistNo)"
                            class="px-4 py-2 bg-white text-black border border-gray-300 rounded-md hover:bg-gray-200 transition duration-200 text-sm">刪除</button>
                    </td>
                </tr>
            </tbody>
        </table>

        <!-- 分頁按鈕 -->
        <div class="mt-4 flex items-center justify-center space-x-4">
            <button @click="changePage(currentPage - 1)" :disabled="currentPage === 1"
                class="px-3 py-1 border rounded-md bg-gray-200 disabled:opacity-50">
                上一頁
            </button>

            <span class="text-lg">第 {{ currentPage }} 頁 / 共 {{ totalPages }} 頁</span>

            <button @click="changePage(currentPage + 1)" :disabled="currentPage === totalPages"
                class="px-3 py-1 border rounded-md bg-gray-200 disabled:opacity-50">
                下一頁
            </button>
        </div>
    </div>
</template>

<style scoped>
button {
    transition: all 0.2s ease-in-out;
}

button:hover:not(:disabled) {
    background-color: #4a90e2;
    color: white;
}
</style>
