<?php
$conn = mysqli_connect("localhost", "root", "", "doan2");
mysqli_set_charset($conn, "utf8");

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $productName = mysqli_real_escape_string($conn, $_POST["productName"]);
    $price = mysqli_real_escape_string($conn, $_POST["price"]);
    $imageUrl = mysqli_real_escape_string($conn, $_POST['image']);
    $customer_id = mysqli_real_escape_string($conn, $_POST['customer_id']);
    $productId = mysqli_real_escape_string($conn, $_POST["productId"]);


    // Lấy tên tệp tin từ đường dẫn hình ảnh
    $image = basename($imageUrl);

    $query_insert = "INSERT INTO tbl_wishlist (productName, price, image, customer_id, productId) VALUES ('$productName', '$price', '$image', '$customer_id', '$productId')";

    $insert_cart = mysqli_query($conn, $query_insert);

    if ($insert_cart) {
        $response = array("status" => "success", "message" => "Added to cart successfully");
        echo json_encode($response);
    } else {
        $response = array("status" => "error", "message" => "Failed to add to cart");
        echo json_encode($response);
    }
}

mysqli_close($conn);
?>
