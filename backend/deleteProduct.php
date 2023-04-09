<?php
    $conn = mysqli_connect("localhost","root","","freshfood");
    $id = $_GET['id'];
    $sql = "delete from sanpham where id = $id";
    $kq = mysqli_query($conn,$sql);
?>