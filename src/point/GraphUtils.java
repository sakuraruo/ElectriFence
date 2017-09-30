package point;

import java.util.Arrays;


/**
 * ���ڵ�������λ�ù�ϵ���ж�
 * 
 * @author zhengtian
 * @date 2013-8-5 ����11:59:35
 */
public class GraphUtils {

	/**
	 * �жϵ��Ƿ��ڶ������(����˼·���ý��㷨)
	 * 
	 * @param point
	 * @param boundaryPoints
	 * @return
	 */
	public static boolean isPointInPolygon(BmapPoint point, BmapPoint[] boundaryPoints) {
		// ��ֹ��һ���������һ������ͬ
		if (boundaryPoints != null && boundaryPoints.length > 0
				&& boundaryPoints[boundaryPoints.length - 1].equals(boundaryPoints[0])) {
			boundaryPoints = Arrays.copyOf(boundaryPoints, boundaryPoints.length - 1);
		}
		int pointCount = boundaryPoints.length;

		// �����жϵ��Ƿ��ڶ���ε���������ڣ�����ڣ����һ���жϣ����򷵻�false
		if (!isPointInRectangle(point, boundaryPoints)) {
			return false;
		}

		// ����������ε�����һ�������غϣ���ôֱ�ӷ���true
		for (int i = 0; i < pointCount; i++) {
			if (point.equals(boundaryPoints[i])) {
				return true;
			}
		}

		/**
		 * ����˼��������X�����߷����������������θ��ߵĽ��㣬�����ż��������ڶ�����⣬�����ڶ�����ڡ����ῼ��һЩ�������������ڶ���ζ�����
		 * �� ���ڶ���α��ϵ����������
		 */
		// X�����������εĽ�����
		int intersectPointCount = 0;
		// X�����������εĽ���Ȩֵ
		float intersectPointWeights = 0;
		// �������ͼ���ʱ����0�Ƚ�ʱ����ݲ�
		double precision = 2e-10;
		// ��P1P2�������˵�
		BmapPoint point1 = boundaryPoints[0], point2;
		// ѭ���ж����еı�
		for (int i = 1; i <= pointCount; i++) {
			point2 = boundaryPoints[i % pointCount];

			/**
			 * ������y�����ڱ�P1P2��y���꿪���䷶Χ֮�⣬��ô���ཻ��
			 */
			if (point.getLat() < Math.min(point1.getLat(), point2.getLat())
					|| point.getLat() > Math.max(point1.getLat(), point2.getLat())) {
				point1 = point2;
				continue;
			}

			/**
			 * �˴��ж���������ཻ
			 */
			if (point.getLat() > Math.min(point1.getLat(), point2.getLat())
					&& point.getLat() < Math.max(point1.getLat(), point2.getLat())) {// ������y�����ڱ�P1P2��y���꿪������
				if (point1.getLng() == point2.getLng()) {// ����P1P2�Ǵ�ֱ��
					if (point.getLng() == point1.getLng()) {
						// �����ڴ�ֱ�ı�P1P2�ϣ�����ڶ������
						return true;
					} else if (point.getLng() < point1.getLng()) {
						// �������ڴ�ֱ�ı�P1P2��ߣ������ñ߱�Ȼ�н���
						++intersectPointCount;
					}
				} else {// ����P1P2��б��
					if (point.getLng() <= Math.min(point1.getLng(), point2.getLng())) {// ��point��x�����ڵ�P1��P2�����
						++intersectPointCount;
					} else if (point.getLng() > Math.min(point1.getLng(), point2.getLng())
							&& point.getLng() < Math.max(point1.getLng(), point2.getLng())) {// ��point��x�����ڵ�P1��P2��x�����м�
						double slopeDiff = 0.0d;
						if (point1.getLat() > point2.getLat()) {
							slopeDiff = (point.getLat() - point2.getLat()) / (point.getLng() - point2.getLng())
									- (point1.getLat() - point2.getLat()) / (point1.getLng() - point2.getLng());
						} else {
							slopeDiff = (point.getLat() - point1.getLat()) / (point.getLng() - point1.getLng())
									- (point2.getLat() - point1.getLat()) / (point2.getLng() - point1.getLng());
						}
						if (slopeDiff > 0) {
							if (slopeDiff < precision) {// ����double�����ڼ���ʱ������ʧ����ƥ��һ�����ݲ�����飬���꾭�ȿ��Դﵽ0.0001
								// ����б��P1P2��
								return true;
							} else {
								// ����б��P1P2�н���
								intersectPointCount++;
							}
						}
					}
				}
			} else {
				// ��P1P2ˮƽ
				if (point1.getLat() == point2.getLat()) {
					if (point.getLng() <= Math.max(point1.getLng(), point2.getLng())
							&& point.getLng() >= Math.min(point1.getLng(), point2.getLng())) {
						// ������ˮƽ�ı�P1P2�ϣ�����ڶ������
						return true;
					}
				}
				/**
				 * �жϵ�ͨ������ζ���
				 */
				if (((point.getLat() == point1.getLat() && point.getLng() < point1.getLng()))
						|| (point.getLat() == point2.getLat() && point.getLng() < point2.getLng())) {
					if (point2.getLat() < point1.getLat()) {
						intersectPointWeights += -0.5;
					} else if (point2.getLat() > point1.getLat()) {
						intersectPointWeights += 0.5;
					}
				}
			}
			point1 = point2;
		}

		if ((intersectPointCount + Math.abs(intersectPointWeights)) % 2 == 0) {// ż���ڶ������
			return false;
		} else { // �����ڶ������
			return true;
		}
	}

	/**
	 * �жϵ��Ƿ��ھ������ھ��α߽��ϣ�Ҳ���ھ�����(������Щ�㣬����һ���������)
	 * 
	 * @param point
	 *            �����
	 * @param boundaryPoints
	 *            ���α߽��
	 * @return
	 */
	public static boolean isPointInRectangle(BmapPoint point, BmapPoint[] boundaryPoints) {
		BmapPoint southWestPoint = getSouthWestPoint(boundaryPoints); // ���Ͻǵ�
		BmapPoint northEastPoint = getNorthEastPoint(boundaryPoints); // �����ǵ�
		return (point.getLng() >= southWestPoint.getLng() && point.getLng() <= northEastPoint.getLng()
				&& point.getLat() >= southWestPoint.getLat() && point.getLat() <= northEastPoint.getLat());

	}

	/**
	 * �����������꣬��һ�����Σ�Ȼ��õ�����������ϽǵĶ�������
	 * 
	 * @param vertexs
	 * @return
	 */
	private static BmapPoint getSouthWestPoint(BmapPoint[] vertexs) {
		double minLng = vertexs[0].getLng(), minLat = vertexs[0].getLat();
		for (BmapPoint bmapPoint : vertexs) {
			double lng = bmapPoint.getLng();
			double lat = bmapPoint.getLat();
			if (lng < minLng) {
				minLng = lng;
			}
			if (lat < minLat) {
				minLat = lat;
			}
		}
		return new BmapPoint(minLng, minLat);
	}

	/**
	 * �����������꣬��һ�����Σ�Ȼ��õ�������ζ����ǵĶ�������
	 * 
	 * @param vertexs
	 * @return
	 */
	private static BmapPoint getNorthEastPoint(BmapPoint[] vertexs) {
		double maxLng = 0.0d, maxLat = 0.0d;
		for (BmapPoint bmapPoint : vertexs) {
			double lng = bmapPoint.getLng();
			double lat = bmapPoint.getLat();
			if (lng > maxLng) {
				maxLng = lng;
			}
			if (lat > maxLat) {
				maxLat = lat;
			}
		}
		return new BmapPoint(maxLng, maxLat);
	}

}

