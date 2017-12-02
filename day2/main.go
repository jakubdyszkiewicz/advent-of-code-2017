package main

import(
    "io/ioutil"
    "strings"
    "fmt"
    "strconv"
)

func ChecksumDivisable(data[][]int) int {
    sum := 0
    for _, line := range data {
        higher, lower := findDivisables(line)
        sum += higher / lower
    }
    return sum
}

func findDivisables(data []int) (int, int) {
    for i := 0; i < len(data); i++ {
        for j := i + 1; j < len(data); j++ {
            higher := data[i]
            lower := data[j]
            if higher < lower {
                higher, lower = lower, higher
            }
            if higher % lower == 0 {
                return higher, lower
            }
        }
    }
    panic("divisables are not found")
}

func Checksum(data [][]int) int {
    sum := 0
    for _, line := range data {
        min, max := minMax(line)
        sum += max - min
    }
    return sum
}

func minMax(data []int) (int, int) {
    min := data[0]
    max := data[0]
    for _, num := range data {
        if min > num {
            min = num
        }
        if max < num {
            max = num
        }
    }
    return min, max
}

func readFile(filename string) [][]int {
    bytes, err := ioutil.ReadFile(filename)
    if err != nil {
        panic(err)
    }
    content := string(bytes)
    content = strings.Trim(content, "\n")
    return parseLines(content)
}

func parseLines(content string) [][]int {
    lines := strings.Split(content, "\n")
    result := [][]int{}

    for _, line := range lines {
        result = append(result, parseLine(line))
    }
    return result
}

func parseLine(content string) []int {
    fields := strings.Fields(content)
    res := []int{}
    for _, num := range fields {
        i, err := strconv.Atoi(num)
        if err != nil {
            panic(err)
        }
        res = append(res, i)
    }
    return res
}
func main() {
    content := readFile("input")
    result := Checksum(content)
    fmt.Println(result)

    resultDivisable := ChecksumDivisable(content)
    fmt.Println(resultDivisable)
}
