/**
 * 
 */
package com.edric.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
/**
 * @author eric.shen <ericsheng@livenation.com>
 * @date Jul 8, 2015
 * @time 4:10:51 PM
 */
public class DBUser {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Configuration myConf = HBaseConfiguration.create();
		myConf.set("hbase.zookeeper.quorum", "192.168.102.69,192.168.102.70,192.168.102.71,192.168.102.72,192.168.102.73");
        myConf.set("hbase.zookeeper.property.clientPort", "2181");
		Connection conn = ConnectionFactory.createConnection(myConf);		
		Table usersTable = conn.getTable(TableName.valueOf("users"));		
//		Put p = new Put(Bytes.toBytes("row1"));
//		p.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes("Mark Twain"));
//		usersTable.put(p);
		Scan scan = new Scan(Bytes.toBytes("row2"), Bytes.toBytes("row3"));
		ResultScanner scanner = usersTable.getScanner(scan);
		for(Result result : scanner) {
			String r = Bytes.toString(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("name")));
			System.out.println(r);
		}
		usersTable.close();
	}

}
