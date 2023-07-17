package com.iteratrlearning.shu_book.chapter_02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class BankStatementAnalyzerSimple {

    private static final String RESOURCES = "src/main/resources/";

    public static void main(final String[] args) throws Exception {
            final Path path = Paths.get(RESOURCES + args[0]);
            // 파일 시스템의 경로 클래스
            final List<String> lines = Files.readAllLines(path); // 행 목록 반환
            /* 파일의 모든 행을 가져온 다음, 각 행에 다음 작업을 수행
            - 콤마로 열 분리
            - 금액 추출
            - 금액을 double로 파싱
            최종적으로 전체 금액의 합계를 얻음

            ??
            - 파일이 비어있다면?
            - 데이터에 문제가 있어서 금액을 파싱하지 못한다면?
            - 행의 데이터가 완벽하지 않다면?
            */
            double total = 0d;
            final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for(final String line: lines) {
                final String[] columns = line.split(",");
                final LocalDate date = LocalDate.parse(columns[0],DATE_PATTERN);
                if(date.getMonth() == Month.JANUARY) {
                    final double amount = Double.parseDouble(columns[1]);
                    total += amount;
                }
            }

            System.out.println("The total for all transactions in January is " + total);
    }
}

