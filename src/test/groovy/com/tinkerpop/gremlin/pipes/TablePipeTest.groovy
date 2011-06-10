package com.tinkerpop.gremlin.pipes

import com.tinkerpop.blueprints.pgm.Graph
import com.tinkerpop.blueprints.pgm.impls.tg.TinkerGraphFactory
import com.tinkerpop.gremlin.Gremlin
import com.tinkerpop.gremlin.pipes.util.Table
import com.tinkerpop.pipes.Pipe
import junit.framework.TestCase

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
class TablePipeTest extends TestCase {

    public void testIsPipe() throws Exception {
        Gremlin.load();
        Graph g = TinkerGraphFactory.createTinkerGraph();
        Table t = new Table();
        assertTrue((g.V.table(t)) instanceof Pipe)
    }

    public void testTablePipe() throws Exception {
        Gremlin.load();
        Graph g = TinkerGraphFactory.createTinkerGraph();
        Table t = new Table();
        g.v(1).as('1').out.as('2').out.as('3').table(t) >> -1;
        assertEquals(t.get(0, 0), g.v(1));
        assertEquals(t.get(0, 1), g.v(4));
        assertEquals(t.get(0, 2), g.v(5));
        assertEquals(t.get(1, 0), g.v(1));
        assertEquals(t.get(0, 1), g.v(4));
        assertEquals(t.get(1, 2), g.v(3));

        t = new Table();
        g.v(1).as('1').out.out.as('2').table(t, {it.name}, {it.name}) >> -1
        assertEquals(t.get(0, '1'), "marko");
        assertEquals(t.get(0, '2'), "ripple");
        assertEquals(t.get(1, '1'), "marko");
        assertEquals(t.get(1, '2'), "lop");

        t.clear()
        g.v(1).as('1').out.out.as('2').table(t) {it.name} {it.name} >> -1
        assertEquals(t.get(0, 0), "marko");
        assertEquals(t.get(0, 1), "ripple");
        assertEquals(t.get(1, 0), "marko");
        assertEquals(t.get(1, 1), "lop");


    }
}