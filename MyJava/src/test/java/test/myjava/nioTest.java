package test.myjava;

import static org.junit.Assert.*;

import org.junit.Test;

import com.myjava.nio.AccessFile;

public class nioTest {

	@Test
	public void testReadFileByStream() {
		AccessFile.ReadFileByStream();
		AccessFile.ReadFileByBufferedStream();
		AccessFile.ReadFileByMappedBuffer();
		//fail("Not yet implemented");
	}

}
