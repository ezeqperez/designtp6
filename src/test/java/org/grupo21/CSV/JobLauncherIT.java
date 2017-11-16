package org.grupo21.CSV;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-batch-context.xml", "classpath:/job.xml" })
public class JobLauncherIT {
    
    @Autowired
    JobLauncherTestUtils jobLauncherTestUtils;
    
    @Test
    public void shouldRunJob() throws Exception {

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        
        Assert.assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());
    }
}