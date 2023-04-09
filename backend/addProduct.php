<?php
     $conn = mysqli_connect("localhost","root","","freshfood");
     if ($_SERVER['REQUEST_METHOD'] == 'POST') {
          $name = $_POST['tenhang'];
          $image = $_POST['image'];
          $dg = $_POST['dongia'];
          $mt = $_POST['mota'];
          $sql = "insert into sanpham(tenhang,image,dongia,mota) values ('$name','$image','$dg','$mt')";
     }
     $kq = mysqli_query($conn,$sql);
     $product = array();
     $temp = array();
     $temp['tenhang'] = $name;
     $temp['image'] = $image;
     $temp['dongia'] = $dg;
     $temp['mota'] = $mt;
     array_push($product,$temp);
     echo json_encode($temp);

     
?>