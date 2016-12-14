package com.simoncat.framework.annotation.excel;

import java.util.Arrays;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class TargetSheetsUtils {

	public static int[] convertToPositionArray(int countOfAllSheets, String regExpression) {
		if (TargetSheets.ALL_SHEETS.equals(regExpression)) {
			return IntStream.range(0, countOfAllSheets).toArray();
		} else {
			return Arrays.stream(regExpression.split(TargetSheets.GROUP_SEPARATOR)).map(group -> {
				String[] range = group.split(TargetSheets.RANGE_SEPARATOR);
				switch (range.length) {
				case 1:
					return IntStream.of(Integer.parseInt(range[0]));
				case 2:
					return IntStream.rangeClosed(Integer.parseInt(range[0]), Integer.parseInt(range[1]));
				default:
					log.warn("Wrong expression {} !", group);
					return IntStream.empty();
				}
			}).reduce(IntStream.empty(), IntStream::concat).toArray();
		}
	}
}
