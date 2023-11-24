package org.pancakeapple.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Configuration
public class UpdateSimilarTask {

    @Value("${spring.task.command}")
    private String command;

    @Value("${spring.task.script-path}")
    private String scriptPath;

    @Scheduled(cron = "0 0 4 * * ?")
    public void updateEmojiSimilarities() {
        try {
            log.info("开始定时更新相似表情包列表...");

            // 构建ProcessBuilder
            ProcessBuilder processBuilder = new ProcessBuilder(command, scriptPath);

            // 启动进程
            Process process = processBuilder.start();

            // 获取进程的输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            // 读取输出
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 等待进程结束
            int exitCode = process.waitFor();
            log.info("定时更新相似推荐列表任务完成，进程退出码：{}",exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
