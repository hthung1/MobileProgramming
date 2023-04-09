<?php
     $conn = mysqli_connect("localhost","root","","freshfood");
     if ($_SERVER['REQUEST_METHOD'] == 'POST') {
          $username = $_POST['username'];
          $mail = $_POST['email'];
          $phone = $_POST['phone'];
          $pass = $_POST['pass'];
          $sql = "insert into user(username,email,phone,pass,role) values ('$username','$mail','$phone','$pass','0')";
     }
     $kq = mysqli_query($conn,$sql);
     $product = array();
     $temp = array();
     $temp['username'] = $username;
     $temp['email'] = $mail;
     $temp['phone'] = $phone;
     $temp['pass'] = $pass;
     array_push($product,$temp);
     echo json_encode($temp);

     
?>