let coffeeMap = {};
let selectedCoffeeId = null;
let coffeeImageIndex = {};

function prevImage(coffeeId) {
    const coffee = coffeeMap[coffeeId];
    if (!coffee || !coffee.imageUrls || coffee.imageUrls.length === 0) return;

    coffeeImageIndex[coffeeId] = (coffeeImageIndex[coffeeId] ?? 0) - 1;
    if (coffeeImageIndex[coffeeId] < 0) {
        coffeeImageIndex[coffeeId] = coffee.imageUrls.length - 1;
    }

    document.getElementById(`coffee-img-${coffeeId}`).src = `/img/${coffee.imageUrls[coffeeImageIndex[coffeeId]]}`;
}

function nextImage(coffeeId) {
    const coffee = coffeeMap[coffeeId];
    if (!coffee || !coffee.imageUrls || coffee.imageUrls.length === 0) return;

    coffeeImageIndex[coffeeId] = (coffeeImageIndex[coffeeId] ?? 0) + 1;
    if (coffeeImageIndex[coffeeId] >= coffee.imageUrls.length) {
        coffeeImageIndex[coffeeId] = 0;
    }

    document.getElementById(`coffee-img-${coffeeId}`).src = `/img/${coffee.imageUrls[coffeeImageIndex[coffeeId]]}`;
}

function loadCoffees() {
    fetch(`/api/products`)
        .then(res => res.json())
        .then(response => {
            let data = [];

            // ✅ 응답 형식 확인 및 안전하게 파싱
            if (Array.isArray(response)) {
                data = response;
            } else if (response && Array.isArray(response.data)) {
                data = response.data;
            } else {
                console.error("🚨 예상치 못한 응답 형식입니다:", response);
                return;
            }

            const tbody = document.getElementById("coffeeTable");
            tbody.innerHTML = "";
            coffeeMap = {};

            data.forEach(coffee => {
                coffeeMap[coffee.id] = coffee;

                const imageUrl = coffee.imageUrls?.[0] || '/img/default.png';

                tbody.innerHTML += `
                    <tr id="coffee-${coffee.id}">
                        <td class="img">
                            <button onclick="prevImage(${coffee.id})">◀</button>
                            <img id="coffee-img-${coffee.id}" src="${imageUrl}" width="80" height="80" alt="${coffee.name}" />
                            <button onclick="nextImage(${coffee.id})">▶</button>
                        </td>
                        <td class="name">${coffee.name}</td>
                        <td class="price">${coffee.price}원</td>
                        <td class="stock">${coffee.quantity}</td>
                        <td><button class="btn btn-dark" onclick="showEditForm(${coffee.id})">수정</button></td>
                        <td><button class="btn btn-dark" onclick="deleteCoffee(${coffee.id})">삭제</button></td>
                    </tr>
                `;
            });
        })
        .catch(err => {
            console.error("❌ 에러 발생:", err);
        });
}

function showEditForm(id) {
    const coffee = coffeeMap[id];
    if (!coffee) return alert("해당 커피 정보를 찾을 수 없습니다.");

    selectedCoffeeId = id;
    document.getElementById("updateCoffeeName").value = coffee.name;
    document.getElementById("updateCoffeePrice").value = coffee.price;
    document.getElementById("updateCoffeeStock").value = coffee.quantity;
    document.getElementById("updateCoffeeImageUrl").value = "";
    document.getElementById("editForm").style.display = "block";
}

function addCoffee() {
    const name = document.getElementById("newCoffeeName").value;
    const price = document.getElementById("newCoffeePrice").value;
    const quantity = document.getElementById("newCoffeeStock").value;
    const images = document.getElementById("newCoffeeImage").files;

    const formData = new FormData();
    formData.append('name', name);
    formData.append('price', price);
    formData.append('quantity', quantity);
    for (let i = 0; i < images.length; i++) {
        formData.append('images', images[i]);
    }

    fetch(`/coffees`, { method: 'POST', body: formData })
        .then(res => res.json())
        .then(() => {
            clearPostForm();
            loadCoffees();
        })
        .catch(console.log);
}

function updateCoffee() {
    if (selectedCoffeeId == null) return;

    const formData = new FormData();
    formData.append('name', document.getElementById('updateCoffeeName').value);
    formData.append('price', document.getElementById('updateCoffeePrice').value);
    formData.append('quantity', document.getElementById('updateCoffeeStock').value);

    const imageInput = document.getElementById('updateCoffeeImageUrl');
    for (let i = 0; i < imageInput.files.length; i++) {
        formData.append('images', imageInput.files[i]);
    }

    fetch(`/coffees/${selectedCoffeeId}`, {
        method: 'PUT',
        body: formData
    })
        .then(res => res.json())
        .then(response => {
            const updated = response.data;
            coffeeMap[updated.id] = updated;
            loadCoffees();
            hideEditForm();
            alert("수정이 완료되었습니다.");
        })
        .catch(console.log);
}

function deleteCoffee(id) {
    if (!id || !confirm("정말 삭제하시겠습니까?")) return;

    fetch(`/coffees/${id}`, { method: 'DELETE' })
        .then(() => {
            delete coffeeMap[id];
            loadCoffees();
        })
        .catch(console.log);
}

function cancelUpdate() {
    hideEditForm();
}

function hideEditForm() {
    document.getElementById("editForm").style.display = "none";
}

function clearPostForm() {
    document.getElementById("newCoffeeName").value = "";
    document.getElementById("newCoffeePrice").value = "";
    document.getElementById("newCoffeeStock").value = "";
    document.getElementById("newCoffeeImage").value = "";
}

window.onload = loadCoffees;