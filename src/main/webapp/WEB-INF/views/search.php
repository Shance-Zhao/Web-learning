<html>
<body>
    <?php
    /* Attempt MySQL server connection. Assuming you are running MySQL
    server with default setting (user 'root' with no password) */
    $link = mysqli_connect("localhost", "root", "12345", "final");
    $val = $_GET["val"];
    // Check connection
    if($link == false){
        die("ERROR: Could not connect. " . mysqli_connect_error());
    }
else{
    // Print host information
    echo "Connect Successfully. Host info: " . mysqli_get_host_info($link);
    $sql="SELECT * FROM product_table WHERE name LIKE '". $val ."%'";
    $result = mysqli_query($link,$sql);

    echo "<table width='100%'>
    <tr><th>Name</th>
        <th>Picture</th>
        <th>Sale</th>
        <th>Description</th>
    </tr>";
    while($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
        echo "<tr>";
        echo "<td>" . $row['name'] . "</td>";
        echo "<td><img src='" . $row['filename'] . "' width='150' height='170'/></td>";
        echo "<td>" . $row['saleprice'] . "</td>";
        echo "<td>" . $row['description'] . "</td>";
        echo "</tr>";
    }
    echo "</table>";
}
    // Close connection
    mysqli_close($link);
    ?>


</body>
</html>