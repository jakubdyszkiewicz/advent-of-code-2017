package main

import (
    "fmt"
    "io/ioutil"
    "strings"
)

func AdjacentSum(input string) int {
    sum := 0
    for i := 1; i < len(input); i++ {
        if input[i] == input[i - 1] {
            sum += characterByteToInt(input[i])
        }
    }
    if len(input) > 1 && input[0] == input[len(input) - 1] {
        sum += characterByteToInt(input[0])
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
