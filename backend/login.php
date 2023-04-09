<?php
    $conn = mysqli_connect("localhost","root","","freshfood");
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $u = $_POST['username'];
        $p = $_POST['pass'];
        $sql = "SELECT * FROM user WHERE username = '$u' AND pass = '$p'";
        $result = mysqli_query($conn, $sql);
        $row = mysqli_fetch_assoc($result);
        // $log = array();
        $temp = array();
        if (mysqli_num_rows($result) > 0) {
            $temp['id'] = $row['id'];
            $temp['username'] = $u;
            $temp['pass'] = $p;
            $temp['role'] = $row['role'];
        }
        array_push($temp);
        echo json_encode($temp);
    }
?>