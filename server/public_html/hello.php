<?php
  $host_name = 'db5000177580.hosting-data.io';
  $database = 'dbs172376';
  $user_name = 'dbu264463';
  $password = 'tvh-fqz@f3mpuJ5';
  $dbh = null;

  try {
    $dbh = new PDO("mysql:host=$host_name; dbname=$database;", $user_name, $password);
    $sql = 'use dbs1722376; SELECT distinct username, password from user_login;';
  } catch (PDOException $e) {
    die("Error!: " . $e->getMessage());
    
  }
  echo "Connection successful";
?>