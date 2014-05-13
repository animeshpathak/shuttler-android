/**
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 * 
 * Copyright : Ambientic, 2014
 * 
 * Author : Pierre-Guillaume Raverdy
 */
package junit.shuttler.clientlib;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ambientic.shuttler.ShuttlerService;
import com.ambientic.shuttler.ShuttlerServiceImpl;
import com.ambientic.shuttler.data.Agency;
import com.ambientic.shuttler.data.PositionInfo;
import com.ambientic.shuttler.data.ShuttleInfo;
import com.ambientic.shuttler.data.StopInfo;

public class BasicTests {
	private static final String URL_SERVER = "http://127.0.0.1:9003/";
	private static final String SAVAC_KEY = "SAVAC";

	String testId = "";
	boolean ok;
	long regNumber;
	ShuttlerService service;

	//
	//
	@Before
	public void before() {
		//
		testId = "Test_" + System.currentTimeMillis();

		//
		service = new ShuttlerServiceImpl(URL_SERVER, SAVAC_KEY);
		assertNotNull(service);
	}

	//
	//
	@After
	public void after() {

	}

	//
	//
	@Test
	public void testRegisterUser() {
		try {
			regNumber = service.registerUser(testId);
			assertTrue(regNumber >= 0);
		} catch (IOException e) {
			fail();
		}

		try {
			regNumber = service.registerUser(testId);
			assertFalse(regNumber >= 0);
		} catch (IOException e) {
			// ok ?? exception since already exist
			// fail();
		}

		try {
			ok = service.unregisterUser(testId);
			assertTrue(ok);
		} catch (IOException e) {
			fail();
		}

		try {
			regNumber = service.registerUser(testId);
			assertTrue(regNumber >= 0);
		} catch (IOException e) {
			fail();
		}

		try {
			ok = service.unregisterUser(testId);
			assertTrue(ok);
		} catch (IOException e) {
			fail();
		}
	}

	//
	//
	@Test
	public void testGetAgencies() {
		try {
			Agency[] agencies = service.getAllAgencies();
			assertNotNull(agencies);

			boolean hasSavac = false;
			for (Agency tmpAgency : agencies) {
				assertNotNull(tmpAgency.getAgency_id());
				assertNotNull(tmpAgency.getAgency_key());

				if (tmpAgency.getAgency_key().equals(SAVAC_KEY)) {
					hasSavac = true;
				}
			}
			assertTrue(hasSavac);
		} catch (IOException e) {
			fail();
		}
	}

	//
	//
	@Test
	public void testNearbyStops() {
		String[] stopids;

		// far away
		try {
			stopids = service.getNearbyStopIds(0, 0);
			assertNotNull(stopids);
			assertTrue(stopids.length == 0);
		} catch (IOException e) {
			fail();
		}

		// rocq (1 stop)
		try {
			stopids = service.getNearbyStopIds(2.102, 48.836);
			assertNotNull(stopids);
			assertTrue(stopids.length == 1);
		} catch (IOException e) {
			fail();
		}

		// auteuil (3 stops)
		try {
			stopids = service.getNearbyStopIds(2.257, 48.847);
			assertNotNull(stopids);
			assertTrue(stopids.length == 3);
		} catch (IOException e) {
			fail();
		}
	}

	//
	//
	@Test
	public void testGetRoute() {
		String[] stopids = null;

		try {
			stopids = service.getNearbyStopIds(2.257, 48.847);
			assertNotNull(stopids);
			assertTrue(stopids.length == 3);

		} catch (IOException e) {
			fail();
		}

		try {
			for (String id : stopids) {
				assertNotNull(id);

				String[] routeIds = service.getRouteIdsForStop(id);
				assertNotNull(routeIds);
			}
		} catch (IOException e) {
			fail();
		}

	}

	//
	//
	@Test
	public void testGetStopInfo() {
		String[] stopids = null;

		try {
			stopids = service.getNearbyStopIds(2.257, 48.847);
			assertNotNull(stopids);
			assertTrue(stopids.length == 3);
		} catch (IOException e) {
			fail();
		}

		try {
			for (String id : stopids) {
				assertNotNull(id);

				StopInfo tmpStopInfo = service.getStopInfo(id);
				assertNotNull(tmpStopInfo);
			}
		} catch (IOException e) {
			fail();
		}
	}

	//
	//
	@Test
	public void testEnterLeaveStop() {
		String[] stopids = null;
		StopInfo tmpStopInfo = null;

		try {
			stopids = service.getNearbyStopIds(2.102, 48.836);
			assertNotNull(stopids);
			assertTrue(stopids.length == 1);
		} catch (IOException e) {
			fail();
		}

		try {
			tmpStopInfo = service.getStopInfo(stopids[0]);
			assertNotNull(tmpStopInfo);
			assertNotNull(tmpStopInfo.getStop_id());
		} catch (IOException e) {
			fail();
		}

		try {
			ok = service.waitAtStop(tmpStopInfo.getStop_id(), testId);
			assertTrue(ok);

			ok = service.leaveStop(testId);
			assertTrue(ok);

		} catch (IOException e) {
			fail();
		}
	}

	//
	//
	@Test
	public void testPostCommentStop() {
		String[] stopids = null;
		StopInfo tmpStopInfo = null;

		try {
			stopids = service.getNearbyStopIds(2.102, 48.836);
			assertNotNull(stopids);
			assertTrue(stopids.length == 1);
			assertNotNull(stopids[0]);
		} catch (IOException e) {
			fail();
		}

		try {
			tmpStopInfo = service.getStopInfo(stopids[0]);
			assertNotNull(tmpStopInfo);
			assertNotNull(tmpStopInfo.getStop_id());
		} catch (IOException e) {
			fail();
		}

		try {
			assertNotNull(tmpStopInfo.getMessages());
			int nb1 = tmpStopInfo.getMessages().length;

			ok = service.waitAtStop(tmpStopInfo.getStop_id(), testId);
			assertTrue(ok);

			ok = service.postComment(testId, "blabla");
			assertTrue(ok);

			tmpStopInfo = service.getStopInfo(stopids[0]);
			int nb2 = tmpStopInfo.getMessages().length;
			assertTrue((nb2 - nb1) == 1);

			ok = service.leaveStop(testId);
			assertTrue(ok);

		} catch (IOException e) {
			fail();
		}
	}

	//
	//
	@Test
	public void testGetShuttles() {
		String[] stopids = null;

		try {
			stopids = service.getNearbyStopIds(2.257, 48.847);
			assertNotNull(stopids);
			assertTrue(stopids.length == 3);

		} catch (IOException e) {
			fail();
		}

		try {
			for (String stopid : stopids) {
				assertNotNull(stopid);

				String[] routeIds = service.getRouteIdsForStop(stopid);
				assertNotNull(routeIds);

				for (String routeid : routeIds) {
					assertNotNull(routeid);

					ShuttleInfo[] shuttles = service
							.getOngoingShuttles(routeid);

					assertNotNull(shuttles);

					for (ShuttleInfo shuttle : shuttles) {
						ShuttleInfo tmpshuttle = service.getShuttleInfo(shuttle
								.getShuttle_id());
						assertNotNull(tmpshuttle);
					}

				}

			}
		} catch (IOException e) {
			fail();
		}

	}

	//
	//
	@Test
	public void testEnterLeaveShuttle() {
		String[] stopids = null;
		String[] routeIds = null;
		ShuttleInfo[] shuttles = null;

		try {
			stopids = service.getNearbyStopIds(2.257, 48.847);
			assertNotNull(stopids);
			assertTrue(stopids.length == 3);

		} catch (IOException e) {
			fail();
		}

		for (String stopid : stopids) {
			assertNotNull(stopid);

			try {
				routeIds = service.getRouteIdsForStop(stopid);
				assertNotNull(routeIds);
			} catch (IOException e) {
				fail();
			}

			for (String routeid : routeIds) {
				assertNotNull(routeid);

				try {
					shuttles = service.getOngoingShuttles(routeid);
				} catch (IOException e) {
					fail();
				}

				for (ShuttleInfo shuttle : shuttles) {
					try {
						ok = service.enterShuttle(testId,
								shuttle.getShuttle_id());
						assertTrue(ok);
					} catch (IOException e) {
						fail();
					}

					try {
						ok = service.enterShuttle(testId,
								shuttle.getShuttle_id());
						assertTrue(ok);
					} catch (IOException e) {
						fail();
					}

					try {
						ok = service.leaveShuttle(testId);
						assertTrue(ok);

					} catch (IOException e) {
						fail();
					}

					try {
						ok = service.enterShuttle(testId,
								shuttle.getShuttle_id());
						assertTrue(ok);

					} catch (IOException e) {
						fail();
					}

				}

			}

		}

	}

	//
	//
	@Test
	public void testPostShuttleComment() {
		String[] stopids = null;
		String[] routeIds = null;
		ShuttleInfo[] shuttles = null;

		try {
			stopids = service.getNearbyStopIds(2.257, 48.847);
			assertNotNull(stopids);
			assertTrue(stopids.length == 3);

		} catch (IOException e) {
			fail();
		}

		for (String stopid : stopids) {
			assertNotNull(stopid);

			try {
				routeIds = service.getRouteIdsForStop(stopid);
				assertNotNull(routeIds);
			} catch (IOException e) {
				fail();
			}

			for (String routeid : routeIds) {
				assertNotNull(routeid);

				try {
					shuttles = service.getOngoingShuttles(routeid);
				} catch (IOException e) {
					fail();
				}

				for (ShuttleInfo shuttle : shuttles) {
					try {
						ok = service.enterShuttle(testId,
								shuttle.getShuttle_id());
						assertTrue(ok);
					} catch (IOException e) {
						fail();
					}

					int nb1 = 0, nb2 = 0;
					ShuttleInfo tmpshuttle;

					try {
						tmpshuttle = service.getShuttleInfo(shuttle
								.getShuttle_id());
						nb1 = tmpshuttle.getMessages().length;
					} catch (IOException e) {
						fail();
					}

					try {
						ok = service.postComment(testId, "shuttle message 1");
						assertTrue(ok);
					} catch (IOException e) {
						fail();
					}

					try {
						tmpshuttle = service.getShuttleInfo(shuttle
								.getShuttle_id());
						nb2 = tmpshuttle.getMessages().length;
						assertTrue((nb2 - nb1) == 1);
					} catch (IOException e) {
						fail();
					}

					try {
						ok = service.leaveShuttle(testId);
						assertTrue(ok);
					} catch (IOException e) {
						fail();
					}

					try {
						ok = service.postComment(testId, "shuttle message 2");
						assertFalse(ok);
					} catch (IOException e) {
						fail();
					}

					try {
						tmpshuttle = service.getShuttleInfo(shuttle
								.getShuttle_id());
						nb2 = tmpshuttle.getMessages().length;
						assertTrue((nb2 - nb1) == 1);
					} catch (IOException e) {
						fail();
					}

				}

			}

		}

	}

	//
	//
	@Test
	public void testUpdatePosition() {
		String[] stopids = null;
		String[] routeIds = null;
		ShuttleInfo[] shuttles = null;

		try {
			stopids = service.getNearbyStopIds(2.257, 48.847);
			assertNotNull(stopids);
			assertTrue(stopids.length == 3);

		} catch (IOException e) {
			fail();
		}

		for (String stopid : stopids) {
			assertNotNull(stopid);

			try {
				routeIds = service.getRouteIdsForStop(stopid);
				assertNotNull(routeIds);
			} catch (IOException e) {
				fail();
			}

			for (String routeid : routeIds) {
				assertNotNull(routeid);

				try {
					shuttles = service.getOngoingShuttles(routeid);
				} catch (IOException e) {
					fail();
				}

				for (ShuttleInfo shuttle : shuttles) {
					try {
						ok = service.enterShuttle(testId,
								shuttle.getShuttle_id());
						assertTrue(ok);
					} catch (IOException e) {
						fail();
					}

					PositionInfo position1 = new PositionInfo();
					position1.setAccur(0.1);
					position1.setAlt(1233);
					position1.setBearing(360);
					position1.setLat(0);
					position1.setLon(0);
					position1.setSpeed(10);

					try {
						ok = service.updatePosition(testId, position1);
						assertTrue(ok);
					} catch (IOException e) {
						fail();
					}

					try {
						ok = service.leaveShuttle(testId);
						assertTrue(ok);
					} catch (IOException e) {
						fail();
					}

					PositionInfo position2 = new PositionInfo();
					position2.setAccur(0.1);
					position2.setAlt(1233);
					position2.setBearing(360);
					position2.setLat(1);
					position2.setLon(1);
					position2.setSpeed(10);

					try {
						ok = service.updatePosition(testId, position2);
						assertFalse(ok);
					} catch (IOException e) {
						fail();
					}

					try {
						ShuttleInfo tmpshuttle = service.getShuttleInfo(shuttle
								.getShuttle_id());
						assertNotNull(tmpshuttle);

						PositionInfo position3 = tmpshuttle.getLastPosition();
						assertNotNull(position3);

						assertTrue(position3.getLat() == position1.getLat());
						assertFalse(position3.getLat() == position2.getLat());

					} catch (IOException e) {
						fail();
					}

				}

			}

		}

	}

}
