package gg.solarmc.uuidutil;

import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UUIDUtilTest {

	private final UUID uuid = UUID.randomUUID();

	@Test
	public void toFromByteArray() {
		assertEquals(uuid, UUIDUtil.fromByteArray(UUIDUtil.toByteArray(uuid)));
	}

	@Test
	public void toBytesLength() {
		assertEquals(16, UUIDUtil.toByteArray(uuid).length);
	}

	@Test
	public void toBytesOffset() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		int bufferSize = random.nextInt(16, 128);
		byte[] bytes = new byte[bufferSize];
		int offset = random.nextInt(bufferSize - 16);

		UUIDUtil.toByteArray(uuid, bytes, offset);
		assertEquals(uuid, UUIDUtil.fromByteArray(bytes, offset));
	}

	@Test
	public void toFromShortString() {
		assertEquals(uuid, UUIDUtil.fromShortString(UUIDUtil.toShortString(uuid)));
	}

	@Test
	public void shortStringLength() {
		assertEquals(32, UUIDUtil.toShortString(uuid).length());
	}

	@Test
	public void contractExpandShortString() {
		String fullUuid = uuid.toString();
		String shortUuid = UUIDUtil.contractFullString(fullUuid);

		assertEquals(fullUuid.replace("-", ""), shortUuid);
		assertEquals(fullUuid, UUIDUtil.expandShortString(shortUuid));
		assertEquals(32, shortUuid.length());
		assertEquals(uuid, UUIDUtil.fromShortString(shortUuid));
	}

}
