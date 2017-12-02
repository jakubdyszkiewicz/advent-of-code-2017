package main

import (
    "fmt"
    "io/ioutil"
    "strings"
)

func AdjacentSum(input string) int {
    sum := 0
    halfway := len(input) / 2
    for i := 0; i < len(input) / 2; i++ {
        if input[i] == input[i + halfway] {
            sum += characterByteToInt(input[i]) * 2
        }
    }
    return sum
}

func characterByteToInt(b byte) int {
    return int(b) - 48
}

func readFile(filename string) string {
    bytes, err := ioutil.ReadFile(filename)
    if err != nil {
        panic(err)
    }
    content := string(bytes)
    return strings.Trim(content, "\n")
}

func main() {
    content := readFile("input")
    result := AdjacentSum(content)
    fmt.Println(result)
}
